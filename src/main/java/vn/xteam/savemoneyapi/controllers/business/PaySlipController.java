package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.core.MessageEntity;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;
import vn.xteam.savemoneyapi.service.IPaySlipService;

import java.util.List;

@RequestMapping(EndpointConfig.PAY_SLIP_API)
@CrossOrigin(origins = "*")
@RestController
public class PaySlipController {
    private final IPaySlipService paySlipService;

    @Autowired
    public PaySlipController(IPaySlipService paySlipService) {
        this.paySlipService = paySlipService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PaySlipEntity>> getAllUsers() {
        List<PaySlipEntity> list = paySlipService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<MessageEntity> createUser(@RequestBody PaySlipEntity body) throws Exception {
        try {
            paySlipService.save(body);
            MessageEntity res = MessageEntity.builder().message("created").status(true).build();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception ex) {
            MessageEntity res = MessageEntity.builder().message(ex.getMessage()).status(false).build();
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }
}
