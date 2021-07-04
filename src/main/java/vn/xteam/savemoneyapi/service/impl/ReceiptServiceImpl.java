package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.*;
import vn.xteam.savemoneyapi.entities.v1.*;
import vn.xteam.savemoneyapi.service.IReceiptService;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements IReceiptService {
    private final ReceiptDao receiptDao;
    private final CustomerDao customerDao;
    private final SavingBookDao savingBookDao;
    private final RuleDao ruleDao;
    private final TransactionDao transactionDao;

    @Autowired
    ReceiptServiceImpl(
            TransactionDao transactionDao,
            ReceiptDao receiptDao,
            CustomerDao customerDao,
            SavingBookDao savingBookDao,
            RuleDao ruleDao) {
        this.receiptDao = receiptDao;
        this.customerDao = customerDao;
        this.savingBookDao = savingBookDao;
        this.ruleDao = ruleDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public List<ReceiptEntity> findAll() {
        return receiptDao.findAll();
    }

    @Override
    public Optional<ReceiptEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(ReceiptEntity receiptEntity) throws Exception {
        String idCard = receiptEntity.getCustomerCode();
        CustomerEntity customer = customerDao.getCustomerByIdCard(idCard);
        if (customer == null) {
            throw new Exception("Không tồn tại khách hàng này");
        }
        String savingBookCode = receiptEntity.getSavingBookCode();
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
        if (receiptEntity.getCreditMoney() < minAmount) {
            throw new Exception("Số tiền không đủ hạn mức");
        }
        if (ruleEntity.getPeriod() != -1) {
            throw new Exception("Không phải sổ không thời hạn");
        }
        receiptEntity.setCustomerCode(idCard);
        receiptEntity.setCustomerId(customer.getId());
        receiptEntity.setSavingBookId(saving.getId());
        receiptDao.create(receiptEntity);
        Double newAmount = receiptEntity.getCreditMoney() + saving.getAmount();
        saving.setAmount(newAmount);
        boolean isSave = savingBookDao.updateOne(saving);
        if (isSave) {
            TransactionEntity transactionEntity = TransactionEntity.builder()
                    .savingBookId(saving.getId())
                    .customerId(customer.getId())
                    .creditMoney(receiptEntity.getCreditMoney())
                    .debitMoney(0.0)
                    .ruleId(saving.getType())
                    .build();
            transactionDao.create(transactionEntity);
        }
    }

    @Override
    public void remove(ReceiptEntity product) {

    }
}
