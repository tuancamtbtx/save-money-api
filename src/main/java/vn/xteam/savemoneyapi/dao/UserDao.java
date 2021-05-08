package vn.xteam.savemoneyapi.dao;

import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.List;

public class UserDao implements IBaseDao<UserEntity> {
    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public UserEntity getById(String id) {
        return null;
    }

    @Override
    public UserEntity updateById(String id) {
        return null;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}
