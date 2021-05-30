package vn.xteam.savemoneyapi.service.impl;

import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.entities.v1.PermissionEntity;
import vn.xteam.savemoneyapi.service.IPermissionService;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {
    @Override
    public List<PermissionEntity> findAll() {
        return null;
    }

    @Override
    public Optional<PermissionEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(PermissionEntity product) {

    }

    @Override
    public void remove(PermissionEntity product) {

    }
}
