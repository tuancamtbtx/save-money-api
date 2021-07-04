package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.dao.RuleDao;
import vn.xteam.savemoneyapi.dao.SavingBookDao;
import vn.xteam.savemoneyapi.dao.TransactionDao;
import vn.xteam.savemoneyapi.entities.form.SavingBookForm;
import vn.xteam.savemoneyapi.entities.v1.*;
import vn.xteam.savemoneyapi.service.ISavingBookService;

import java.util.List;
import java.util.Optional;

@Service
public class SavingBookServiceImpl implements ISavingBookService {
    private final SavingBookDao savingBookDao;
    private final CustomerDao customerDao;
    private final RuleDao ruleDao;
    private final TransactionDao transactionDao;
    @Autowired
    public SavingBookServiceImpl(SavingBookDao savingBookDao,CustomerDao customerDao,RuleDao ruleDao,TransactionDao transactionDao) {
        this.savingBookDao = savingBookDao;
        this.customerDao = customerDao;
        this.ruleDao = ruleDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public List<SavingBookEntity> findAll() {
        return savingBookDao.findAll();
    }

    @Override
    public Optional<SavingBookEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(SavingBookForm body) throws Exception {

        String idCard = body.getIdCard();
        CustomerEntity customerByIdCard = customerDao.getCustomerByIdCard(idCard);
        if(customerByIdCard == null) {
            throw new Exception("Không tìm thấy khách hàng");
        }
        RuleEntity rule = ruleDao.findById(body.getType().intValue());
        if(body.getAmount() < rule.getMinAmount()) {
            throw new Exception("Số tiền nhỏ hơn hạn mức thấp nhất");
        }
        SavingBookEntity savingBookEntity = SavingBookEntity.builder()
                .customerId(customerByIdCard.getId())
                .amount(body.getAmount())
                .interestRate(rule.getInterestRate())
                .period(rule.getPeriod())
                .type(body.getType())
                .build();
        Long id = savingBookDao.create(savingBookEntity);
        if(id > 0) {
            TransactionEntity transactionEntity = TransactionEntity.builder()
                    .savingBookId(id)
                    .customerId(savingBookEntity.getCustomerId())
                    .creditMoney(savingBookEntity.getAmount())
                    .debitMoney(0.0)
                    .ruleId(body.getType())
                    .build();
            transactionDao.create(transactionEntity);
        }
    }

    @Override
    public void remove(SavingBookEntity product) {

    }

    @Override
    public List<SavingBookReport> report() {
        return savingBookDao.getReport();
    }
}
