package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.common.utils.JWTUtils;
import vn.xteam.savemoneyapi.dao.UserDao;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserDao userDao;

    @Autowired
    public AuthServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity login(String username, String password) {
        String whereClause = String.format("username = %s and password = %s", username, password);
        UserEntity user = userDao.findOne(whereClause);
        String token = JWTUtils.generateToken(user);
        user.setToken(token);
        return user;
    }

    @Override
    public UserEntity logout() {
        return UserEntity.builder()
                .userName("tuancam")
                .id("2")
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }

    @Override
    public UserEntity getMe(String token) {
        return UserEntity.builder()
                .userName("tuancam")
                .id("2")
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }
}
