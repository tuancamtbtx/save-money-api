package vn.xteam.savemoneyapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.dao.PaySlipDao;
import vn.xteam.savemoneyapi.dao.SavingBookDao;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.service.IPaySlipService;

import java.util.List;
import java.util.Optional;

@Service
public class PaySlipServiceImpl implements IPaySlipService {
    private final PaySlipDao paySlipDao;
    private final CustomerDao customerDao;
    private final SavingBookDao savingBookDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaySlipServiceImpl.class.getName());

    @Autowired
    public PaySlipServiceImpl(PaySlipDao paySlipDao, CustomerDao customerDao, SavingBookDao savingBookDao) {
        this.paySlipDao = paySlipDao;
        this.customerDao = customerDao;
        this.savingBookDao = savingBookDao;
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
            throw new Exception("Not Exist customer");
        }
        String savingBookCode = paySlipEntity.getSavingBookCode();
        String whereClause = String.format("s.code = '%s' AND s.customer_id = %s", savingBookCode, customer.getId());
        SavingBookEntity saving = savingBookDao.findOne(whereClause);
        if (saving == null) {
            throw new Exception("Not Exist SavingBook");
        }
        paySlipEntity.setCustomerCode(idCard);
        paySlipEntity.setCustomerId(customer.getId());
        paySlipEntity.setSavingBookId(saving.getId());
        paySlipDao.create(paySlipEntity);
    }

    @Override
    public void remove(PaySlipEntity product) {

    }
}
