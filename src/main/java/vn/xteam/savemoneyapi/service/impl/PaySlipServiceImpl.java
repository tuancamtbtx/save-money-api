package vn.xteam.savemoneyapi.service.impl;

import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;
import vn.xteam.savemoneyapi.service.IPaySlipService;

import java.util.List;
import java.util.Optional;

@Service
public class PaySlipServiceImpl implements IPaySlipService {
    @Override
    public List<PaySlipEntity> findAll() {
        return null;
    }

    @Override
    public Optional<PaySlipEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(PaySlipEntity product) {

    }

    @Override
    public void remove(PaySlipEntity product) {

    }
}
