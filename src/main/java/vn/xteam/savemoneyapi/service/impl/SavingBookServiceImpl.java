package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.dao.RuleDao;
import vn.xteam.savemoneyapi.dao.SavingBookDao;
import vn.xteam.savemoneyapi.entities.form.SavingBookForm;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.RuleEntity;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.service.ISavingBookService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SavingBookServiceImpl implements ISavingBookService {
    private final SavingBookDao savingBookDao;
    private final CustomerDao customerDao;
    private final RuleDao ruleDao;

    @Autowired
    public SavingBookServiceImpl(SavingBookDao savingBookDao,CustomerDao customerDao,RuleDao ruleDao) {
        this.savingBookDao = savingBookDao;
        this.customerDao = customerDao;
        this.ruleDao = ruleDao;
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
            throw new Exception("Creating customerByIdCard failed, no ID obtained.");
        }
        RuleEntity rule = ruleDao.findById(body.getType());
        if(body.getAmount() < rule.getMinAmount()) {
            throw new Exception("Not Enough Money");
        }
        SavingBookEntity savingBookEntity = SavingBookEntity.builder()
                .customerId(customerByIdCard.getId())
                .amount(body.getAmount())
                .interestRate(rule.getInterestRate())
                .period(rule.getPeriod())
                .type(body.getType())
                .build();
        savingBookDao.create(savingBookEntity);
        System.out.println(customerByIdCard);
    }

    @Override
    public void remove(SavingBookEntity product) {

    }
}
