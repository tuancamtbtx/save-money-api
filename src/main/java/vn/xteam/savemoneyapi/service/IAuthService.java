package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.UserEntity;

public interface IAuthService {
    public UserEntity login(String username, String password);
    public UserEntity logout();
    public UserEntity getMe(String token);
}
