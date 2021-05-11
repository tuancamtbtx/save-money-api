package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.UserDao;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserEntity findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public boolean save(UserEntity user) {
        return userDao.create(user);
    }

    @Override
    public boolean remove(String id) {
        return userDao.removeById(id);
    }

    @Override
    public boolean update(UserEntity user) {
        return  userDao.updateById(user);
    }
}
