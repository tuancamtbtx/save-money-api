package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserEntity> findAll();
    UserEntity findById(String id);
    boolean save(UserEntity product);
    boolean remove(String id) throws SQLException;
    boolean update(UserEntity product);

}
