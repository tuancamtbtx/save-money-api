//package vn.xteam.savemoneyapi.controllers.adminstration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import vn.xteam.savemoneyapi.entities.v1.UserEntity;
//import vn.xteam.savemoneyapi.service.IUserService;
//
//import java.util.List;
//
//@RequestMapping("api/v1/users")
//@RestController
//public class UserController {
//    @Autowired
//    private IUserService userService;
//
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<List<UserEntity>> getAllUsers() {
//        List<UserEntity> list = userService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<UserEntity> getInfo(@PathVariable String id) {
//        UserEntity info = userService.findById(id);
//        return new ResponseEntity<>(info, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/", produces = "application/json")
//    public ResponseEntity<UserEntity> createUser() {
//        userService.save(null);
//        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
//    }
//
//    @PutMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable String id) {
//        UserEntity user = userService.findById(id);
//        boolean check = userService.update(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/", produces = "application/json")
//    public ResponseEntity<List<UserEntity>> removeUser() {
//        List<UserEntity> me = userService.findAll();
//        return new ResponseEntity<>(me, HttpStatus.OK);
//    }
//}
