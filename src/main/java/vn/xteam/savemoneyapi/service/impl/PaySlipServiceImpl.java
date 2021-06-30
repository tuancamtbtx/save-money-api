package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.PaySlipDao;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;
import vn.xteam.savemoneyapi.service.IPaySlipService;

import java.util.List;
import java.util.Optional;

@Service
public class PaySlipServiceImpl implements IPaySlipService {
    private final PaySlipDao paySlipDao;

    @Autowired
    public PaySlipServiceImpl(PaySlipDao paySlipDao) {
        this.paySlipDao = paySlipDao;
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
    public void save(PaySlipEntity product) {

    }

    @Override
    public void remove(PaySlipEntity product) {

    }
}
