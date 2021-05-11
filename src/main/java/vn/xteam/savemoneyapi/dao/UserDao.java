package vn.xteam.savemoneyapi.dao;

import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements IBaseDao<UserEntity> {
    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity u1 = UserEntity.builder()
                .username("tuan")
                .id("2")
                .email("tuan.nguyen15@tiki.vn")
                .build();
        list.add(u1);
        return list;
    }

    @Override
    public UserEntity findById(String id) {
        return UserEntity.builder()
                .username("tuan")
                .id("2")
                .email("tuan.nguyen15@tiki.vn")
                .build();
    }

    @Override
    public boolean updateById(UserEntity user) {
        return false;
    }

    @Override
    public boolean create(UserEntity entity) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}
