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
@CrossOrigin(origins = "*")
@RestController
public class AuthController {
    private final IAuthService authService;

    @Autowired
    public  AuthController(IAuthService authService){
        this.authService = authService;
    }

    @GetMapping(path = "/me", produces = "application/json")
    public ResponseEntity<UserEntity> getMe(@RequestHeader("Authorization") String token) {
        UserEntity me = authService.getMe(token);
        me.setToken(token);
        return new ResponseEntity<>(me, HttpStatus.OK);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userData) {
        String email = userData.getEmail();
        String password = userData.getPassword();
        UserEntity user = UserEntity.builder().userName("tuancam").token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIyYmVhMmY1NjE1OWJkMTg1ZTk0M2Q1Y2ExMDg1M2JkZDBhNjRjM2ZlZDQ2NDRjYzNjNDFjZDNmMWI3ZGU2ZGNiIiwidHlwZSI6ImFjY2Vzc190b2tlbiIsInRpbWUiOjE2MTQ2MTE2MjMxNDEsImlhdCI6MTYxNDYxMTYyMywiZXhwIjoxNjE5Nzk1NjIzfQ.q98HCMIrUTFfbT_EVggWV8WMZoYEVwy_Kbic8szOtO").build();
//        UserEntity user = authService.login(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<UserEntity> logout() {
        UserEntity user = authService.logout();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
