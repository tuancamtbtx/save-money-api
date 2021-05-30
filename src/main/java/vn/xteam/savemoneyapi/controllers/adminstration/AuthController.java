package vn.xteam.savemoneyapi.controllers.adminstration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.common.utils.HttpUtils;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IAuthService;
@RequestMapping(EndpointConfig.AUTH_API)
@RestController
public class AuthController {
    private final IAuthService authService;

    @Autowired
    public  AuthController(IAuthService authService){
        this.authService = authService;
    }

    @GetMapping(path = "/me", produces = "application/json")
    public ResponseEntity<UserEntity> getMe(@RequestHeader("authorization") String token) {
        UserEntity me = authService.getMe(token);
        me.setToken(token);
        return new ResponseEntity<>(me, HttpStatus.OK);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userData) {
        System.out.println(userData);
        String email = userData.getEmail();
        String password = userData.getPassword();
//        UserEntity user = authService.login(email, password);
        System.out.println("Email: "+ email + "- Password: " + password);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<UserEntity> logout() {
        UserEntity user = authService.logout();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
