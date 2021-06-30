package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.CustomerDao;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.service.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private  final CustomerDao customerDao;
    public CustomerService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    @Override
    public boolean save(CustomerEntity customer) {
        return customerDao.create(customer);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerDao.findAll();
    }
}
