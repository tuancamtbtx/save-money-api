package vn.xteam.savemoneyapi.controllers.adminstration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.service.ICustomerService;

import java.util.List;

@RequestMapping(EndpointConfig.CUSTOMER_API)
@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<CustomerEntity>> getList(@RequestHeader("authorization") String token) {
        List<CustomerEntity> list = customerService.findAll();
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<CustomerEntity>> save(@RequestBody CustomerEntity userData) {
        customerService.save(userData);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
