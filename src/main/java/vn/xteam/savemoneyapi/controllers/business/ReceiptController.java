package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
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
    public ResponseEntity<ReceiptEntity> createUser(@RequestBody ReceiptEntity body) {
        receiptService.save(body);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


}
