package vn.xteam.savemoneyapi.service.impl;

import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.service.ISavingBookService;

import java.util.List;
import java.util.Optional;

@Service
public class SavingBookServiceImpl implements ISavingBookService {
    @Override
    public List<SavingBookEntity> findAll() {
        return null;
    }

    @Override
    public Optional<SavingBookEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(SavingBookEntity product) {

    }

    @Override
    public void remove(SavingBookEntity product) {

    }
}
