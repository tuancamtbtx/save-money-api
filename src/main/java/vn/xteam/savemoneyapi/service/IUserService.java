package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Integer id);
    void save(UserEntity product);
    void remove(UserEntity product);
}
