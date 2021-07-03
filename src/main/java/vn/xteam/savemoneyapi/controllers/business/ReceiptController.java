package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.core.MessageEntity;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;
import vn.xteam.savemoneyapi.service.IReceiptService;

import java.util.List;

@RequestMapping(EndpointConfig.RECEIPT_API)
@CrossOrigin(origins = "*")
@RestController
public class ReceiptController {
    private final IReceiptService receiptService;

    @Autowired
    public ReceiptController(IReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ReceiptEntity>> getAllUsers() {
        List<ReceiptEntity> list = receiptService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<MessageEntity> createUser(@RequestBody ReceiptEntity body) throws Exception {
        try {
            receiptService.save(body);
            MessageEntity res = MessageEntity.builder().message("created").status(true).build();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception ex) {
            MessageEntity res = MessageEntity.builder().message(ex.getMessage()).status(false).build();
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

        }
    }


}
