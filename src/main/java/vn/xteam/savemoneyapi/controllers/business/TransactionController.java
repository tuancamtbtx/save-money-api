package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.v1.TransactionReport;
import vn.xteam.savemoneyapi.service.ITransactionService;

import java.util.List;

@RequestMapping(EndpointConfig.TRANSACTION_API)
@CrossOrigin(origins = "*")
@RestController
public class TransactionController {
    private final ITransactionService service;

    @Autowired
    private TransactionController(ITransactionService service) {
        this.service = service;
    }

    @GetMapping(path = "/report",produces = "application/json")
    public ResponseEntity<List<TransactionReport>> getAllUsers() {
        List<TransactionReport> list = service.report();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
