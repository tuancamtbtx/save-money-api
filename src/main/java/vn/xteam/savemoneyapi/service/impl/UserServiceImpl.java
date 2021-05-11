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
        return userDao.getAll();
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        UserEntity user = userDao.getById(id.toString());
        return Optional.of(user);
    }

    @Override
    public void save(UserEntity user) {
        System.out.println("Saved user");
    }

    @Override
    public void remove(UserEntity user) {
        System.out.println("Removed user");

    }
}
