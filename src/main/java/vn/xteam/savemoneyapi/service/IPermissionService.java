package vn.xteam.savemoneyapi.service;


import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<PermissionEntity> findAll();
    Optional<PermissionEntity> findById(Integer id);
    void save(PermissionEntity product);
    void remove(PermissionEntity product);
}
