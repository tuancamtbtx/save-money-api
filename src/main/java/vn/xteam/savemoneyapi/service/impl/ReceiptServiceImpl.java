package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.dao.ReceiptDao;
import vn.xteam.savemoneyapi.dao.SavingBookDao;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.service.IReceiptService;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements IReceiptService {
    private final ReceiptDao receiptDao;
    private final CustomerDao customerDao;
    private final SavingBookDao savingBookDao;

    @Autowired
    ReceiptServiceImpl(ReceiptDao receiptDao, CustomerDao customerDao, SavingBookDao savingBookDao) {
        this.receiptDao = receiptDao;
        this.customerDao = customerDao;
        this.savingBookDao = savingBookDao;
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
            throw new Exception("Not Exist customer");
        }
        String savingBookCode = receiptEntity.getSavingBookCode();
        String whereClause = String.format("s.code = '%s' AND s.customer_id = %s", savingBookCode, customer.getId());
        SavingBookEntity saving = savingBookDao.findOne(whereClause);
        if (saving == null) {
            throw new Exception("Not Exist SavingBook");
        }
        receiptEntity.setCustomerCode(idCard);
        receiptEntity.setCustomerId(customer.getId());
        receiptEntity.setSavingBookId(saving.getId());
        receiptDao.create(receiptEntity);
    }

    @Override
    public void remove(ReceiptEntity product) {

    }
}
