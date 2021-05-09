package vn.xteam.savemoneyapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @GetMapping(path = "/users", produces = "application/json")
    public List<UserEntity> getListUsers() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity user = UserEntity.builder()
                .email("nguyenvantuan140397@gmail.com")
                .firstName("Van Tuan")
                .id(1)
                .lastName("Nguyen")
                .build();
        list.add(user);
        return list;
    }
}
