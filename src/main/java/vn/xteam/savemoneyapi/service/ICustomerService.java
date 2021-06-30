package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;

import java.util.List;

public interface ICustomerService {
    boolean save(CustomerEntity event);

    List<CustomerEntity> findAll();
}
