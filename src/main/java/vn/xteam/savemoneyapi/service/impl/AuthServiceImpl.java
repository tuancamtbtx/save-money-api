package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.common.utils.JWTUtils;
import vn.xteam.savemoneyapi.dao.UserDao;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity login(String username, String password) {
        UserEntity user = UserEntity.builder()
                .username("tuan")
                .id(2)
                .token("")
                .email("tuan.nguyen15@tiki.vn")
                .build();
        String token = JWTUtils.generateToken(user);
        user.setToken(token);
        return user;
    }

    @Override
    public UserEntity logout() {
        return UserEntity.builder()
                .username("tuan")
                .id(2)
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }

    @Override
    public UserEntity getMe(String token) {
        return UserEntity.builder()
                .username("tuan")
                .id(2)
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }
}
