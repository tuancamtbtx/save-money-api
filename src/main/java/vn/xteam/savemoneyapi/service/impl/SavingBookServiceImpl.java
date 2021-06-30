package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.SavingBookDao;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.service.ISavingBookService;

import java.util.List;
import java.util.Optional;

@Service
public class SavingBookServiceImpl implements ISavingBookService {
    private final SavingBookDao savingBookDao;

    @Autowired
    public SavingBookServiceImpl(SavingBookDao savingBookDao) {
        this.savingBookDao = savingBookDao;
    }

    @Override
    public List<SavingBookEntity> findAll() {
        return savingBookDao.findAll();
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
