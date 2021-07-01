package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.dao.IdentityCardDao;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.IdentityCardEntity;
import vn.xteam.savemoneyapi.service.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerDao customerDao;
    private final IdentityCardDao iCardDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, IdentityCardDao iCardDao) {
        this.customerDao = customerDao;
        this.iCardDao = iCardDao;
    }

    @Override
    public boolean save(CustomerEntity customer) {
        Long customerId = customerDao.create(customer);
        IdentityCardEntity cmnd = IdentityCardEntity.builder()
                .customerId(customerId)
                .providedAt(customer.getIdCard().getProvidedAt())
                .code(customer.getIdCard().getCode())
                .build();
        iCardDao.create(cmnd);
        return true;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerDao.findAll();
    }
}
