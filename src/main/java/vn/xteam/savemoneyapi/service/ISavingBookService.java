package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ISavingBookService {
    List<SavingBookEntity> findAll();
    Optional<SavingBookEntity> findById(String id);

    void save(SavingBookEntity product);
    void remove(SavingBookEntity product);
}
