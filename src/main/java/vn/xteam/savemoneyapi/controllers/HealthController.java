package vn.xteam.savemoneyapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employees")
public class HealthController {
    @GetMapping(path="/", produces = "application/json")
    public String checkHealth() {
        return "Save money api is live";
    }
}
