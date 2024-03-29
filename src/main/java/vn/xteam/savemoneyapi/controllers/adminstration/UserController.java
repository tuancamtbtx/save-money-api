package vn.xteam.savemoneyapi.controllers.adminstration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;
import vn.xteam.savemoneyapi.service.IUserService;

import java.sql.SQLException;
import java.util.List;

@RequestMapping(EndpointConfig.USER_API)
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> list = userService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserEntity> getInfo(@PathVariable String id) {
        String whereClause = String.format("WHERE id = %s", id);
        UserEntity info = userService.findById(whereClause);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userData) {
        userService.save(userData);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String id) {
        UserEntity user = userService.findById(id);
        boolean check = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserEntity> removeUser(@PathVariable String id) throws SQLException {
        boolean check = userService.remove(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
