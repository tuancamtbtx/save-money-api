package vn.xteam.savemoneyapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.common.utils.TimeUtils;
import vn.xteam.savemoneyapi.dao.*;
import vn.xteam.savemoneyapi.entities.v1.*;
import vn.xteam.savemoneyapi.service.IPaySlipService;

import java.sql.Timestamp;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PaySlipServiceImpl implements IPaySlipService {
    private final PaySlipDao paySlipDao;
    private final CustomerDao customerDao;
    private final SavingBookDao savingBookDao;

    private final RuleDao ruleDao;
    private final TransactionDao transactionDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaySlipServiceImpl.class.getName());

    @Autowired
    public PaySlipServiceImpl(
            PaySlipDao paySlipDao,
            CustomerDao customerDao,
            SavingBookDao savingBookDao,
            TransactionDao transactionDao,
            RuleDao ruleDao
    ) {
        this.paySlipDao = paySlipDao;
        this.customerDao = customerDao;
        this.savingBookDao = savingBookDao;
        this.ruleDao = ruleDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public List<PaySlipEntity> findAll() {
        return paySlipDao.findAll();
    }

    @Override
    public Optional<PaySlipEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(PaySlipEntity paySlipEntity) throws Exception {
        String idCard = paySlipEntity.getCustomerCode();
        CustomerEntity customer = customerDao.getCustomerByIdCard(idCard);
        if (customer == null) {
            throw new Exception("Không tồn tại khách hàng này");
        }
        String savingBookCode = paySlipEntity.getSavingBookCode();
        String whereClause = String.format("s.code = '%s' AND s.customer_id = %s", savingBookCode, customer.getId());
        SavingBookEntity saving = savingBookDao.findOne(whereClause);
        if (saving == null) {
            throw new Exception("Không tồn tại sổ này");
        }
        if (saving.getStatus() == 2) {
            throw new Exception("Sổ tiết kiệm đã đóng");
        }
        RuleEntity ruleEntity = ruleDao.findById(saving.getType().intValue());
        Double minAmount = ruleEntity.getMinAmount();
        if (paySlipEntity.getDebitMoney() < minAmount) {
            throw new Exception("Số tiền không đủ hạn mức");
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp sendTime = saving.getCreatedAt();
        double interestMoney = 0.0;
        double debitMoney = 0.0;
        Period timeDiff = TimeUtils.getTimeDiff(sendTime, currentTime);
        int status = saving.getStatus();
        int months = timeDiff.getYears() * 12 + timeDiff.getMonths();
        System.out.println(currentTime);
        System.out.println(sendTime);
        System.out.println("ababababba:" + months);
        float rate = saving.getInterestRate() / 12;
        if (ruleEntity.getPeriod() == -1) {
            if (timeDiff.getDays() < 15) {
                throw new Exception("Chưa đủ thời gian rút tiền");
            }
            if (paySlipEntity.getDebitMoney() > saving.getAmount()) {
                throw new Exception("Số tiền vượt quá hạn mức tiết kiệm");
            }
            // rut tien voi so khong ky han
            interestMoney = months * rate * saving.getAmount();
            debitMoney = paySlipEntity.getDebitMoney() + interestMoney;
            if (paySlipEntity.getDebitMoney().equals(saving.getAmount())) {
                status = 2;
            }
        } else {
            // rut tien voi so co ky han (3 thang, 6 thang)
            if (months < saving.getPeriod()) {
                throw new Exception("Chưa đủ thời gian rút tiền");
            }
            if (paySlipEntity.getDebitMoney() < saving.getAmount()) {
                throw new Exception("Số tiền rút nhỏ hơn số dư tiết kiệm");
            }
            int countExpired = months / ruleEntity.getPeriod();
            interestMoney = countExpired * months * rate * saving.getAmount();
            debitMoney = saving.getAmount() + interestMoney;
            status = 2;
        }
        Double newAmount = saving.getAmount() - paySlipEntity.getDebitMoney();
        saving.setAmount(newAmount);
        saving.setStatus(status);
        paySlipEntity.setCustomerCode(idCard);
        paySlipEntity.setCustomerId(customer.getId());
        paySlipEntity.setSavingBookId(saving.getId());
        paySlipDao.create(paySlipEntity);
        boolean isSave = savingBookDao.updateOne(saving);
        if (isSave) {
            TransactionEntity transactionEntity = TransactionEntity.builder()
                    .savingBookId(saving.getId())
                    .customerId(customer.getId())
                    .creditMoney(0.0)
                    .debitMoney(debitMoney)
                    .ruleId(saving.getType())
                    .build();
            transactionDao.create(transactionEntity);
        }
    }

    @Override
    public void remove(PaySlipEntity product) {

    }
}
