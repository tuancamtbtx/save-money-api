package vn.xteam.savemoneyapi.controllers;

import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.entities.v1.HealthEntity;

@RequestMapping("api")
@RestController
public class HealthController {
    @GetMapping(path="/health", produces = "application/json")
    public ResponseEntity<HealthEntity> checkHealth() {
        HealthEntity res = HealthEntity.builder()
                .message("Save money api is live")
                .status(true)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
