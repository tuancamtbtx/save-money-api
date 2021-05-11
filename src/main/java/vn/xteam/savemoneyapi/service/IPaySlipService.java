package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;

import java.util.List;
import java.util.Optional;

public interface IPaySlipService {
    List<PaySlipEntity> findAll();

    Optional<PaySlipEntity> findById(Integer id);

    void save(PaySlipEntity product);

    void remove(PaySlipEntity product);
}
