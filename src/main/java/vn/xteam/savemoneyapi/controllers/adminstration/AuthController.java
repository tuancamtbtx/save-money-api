package vn.xteam.savemoneyapi.controllers.adminstration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IAuthService;

@RequestMapping("api/v1/auth")
@RestController
public class AuthController {
    @Autowired
    private IAuthService userService;

    @GetMapping(path = "/me", produces = "application/json")
    public ResponseEntity<UserEntity> getMe() {
        UserEntity me = userService.getMe();
        return new ResponseEntity<>(me, HttpStatus.OK);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserEntity> login() {
        UserEntity user = userService.login();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<UserEntity> logout() {
        UserEntity user = userService.login();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
