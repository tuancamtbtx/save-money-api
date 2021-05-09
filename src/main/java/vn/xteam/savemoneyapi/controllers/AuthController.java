package vn.xteam.savemoneyapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class AuthController {

    @GetMapping(path = "/auth/me", produces = "application/json")
    public UserEntity getMe() {
        return null;
    }

    @GetMapping(path = "/auth/login", produces = "application/json")
    public UserEntity login() {
        return null;
    }

    @GetMapping(path = "/auth/logout", produces = "application/json")
    public UserEntity logout() {
        return null;
    }
}
