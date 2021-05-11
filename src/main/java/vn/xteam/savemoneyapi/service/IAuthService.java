package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.UserEntity;

public interface IAuthService {
    public UserEntity login();
    public UserEntity logout();
    public UserEntity getMe();
}
