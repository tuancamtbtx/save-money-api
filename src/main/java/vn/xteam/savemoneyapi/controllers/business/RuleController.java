package vn.xteam.savemoneyapi.controllers.business;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.core.MessageEntity;
import vn.xteam.savemoneyapi.entities.v1.RuleEntity;
import vn.xteam.savemoneyapi.service.IRuleService;

import java.util.List;

@RequestMapping(EndpointConfig.RULE_API)
@CrossOrigin(origins = "*")
@RestController
public class RuleController {
    private final IRuleService ruleService;

    public RuleController(IRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RuleEntity>> getAllUsers() {
        List<RuleEntity> list = ruleService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<MessageEntity> createUser(@RequestBody RuleEntity body) throws Exception {
        try {
            ruleService.save(body);
            MessageEntity res = MessageEntity.builder().message("created").status(true).build();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception ex) {
            MessageEntity res = MessageEntity.builder().message(ex.getMessage()).status(false).build();
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }
}
