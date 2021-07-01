package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.form.SavingBookForm;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;

import java.util.List;
import java.util.Optional;

public interface ISavingBookService {
    List<SavingBookEntity> findAll();

    Optional<SavingBookEntity> findById(String id);

    void save(SavingBookForm product) throws Exception;

    void remove(SavingBookEntity product);
}
