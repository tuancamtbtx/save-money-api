package vn.xteam.savemoneyapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @GetMapping(path="/", produces = "application/json")
    public String getListUsers() {
        return "Save money api is live";
    }
}
