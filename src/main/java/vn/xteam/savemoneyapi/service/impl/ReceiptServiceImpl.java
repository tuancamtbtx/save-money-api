package vn.xteam.savemoneyapi.service.impl;

import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;
import vn.xteam.savemoneyapi.service.IReceiptService;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements IReceiptService {
    @Override
    public List<ReceiptEntity> findAll() {
        return null;
    }

    @Override
    public Optional<ReceiptEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(ReceiptEntity product) {

    }

    @Override
    public void remove(ReceiptEntity product) {

    }
}