package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements IUserService {

    private UserDao userDao;

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserEntity findById(String whereClause) {
        return userDao.findOne(whereClause);
    }

    @Override
    public boolean save(UserEntity user) {
        return userDao.create(user);
    }

    @Override
    public boolean remove(String id) throws SQLException {
        return userDao.removeOne(id);
    }

    @Override
    public boolean update(UserEntity user) {
        return  userDao.updateOne(user);
    }
}
