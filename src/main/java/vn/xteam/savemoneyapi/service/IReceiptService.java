package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;

import java.util.List;
import java.util.Optional;

public interface IReceiptService {
    List<ReceiptEntity> findAll();
    Optional<ReceiptEntity> findById(Integer id);
    void save(ReceiptEntity product) throws Exception;
    void remove(ReceiptEntity product);
}
