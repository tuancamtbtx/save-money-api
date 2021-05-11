package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.UserDao;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity login() {
        return UserEntity.builder()
                .lastName("tuan")
                .id(2)
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }

    @Override
    public UserEntity logout() {
        return UserEntity.builder()
                .lastName("tuan")
                .id(2)
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }

    @Override
    public UserEntity getMe() {
        return UserEntity.builder()
                .lastName("tuan")
                .id(2)
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }
}
