package vn.xteam.savemoneyapi.service;


import vn.xteam.savemoneyapi.entities.v1.PermissionEntity;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<PermissionEntity> findAll();
    Optional<PermissionEntity> findById(Integer id);
    void save(PermissionEntity product);
    void remove(PermissionEntity product);
}
