package vn.xteam.savemoneyapi.controllers;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HealthController {

    @GetMapping(path="/health", produces = "application/json")
    public JsonObject checkHealth() {
        JsonObject res = new JsonObject();
        res.addProperty("status", true);
        res.addProperty("message", "Save money api is live ");
        return res;
    }
}
