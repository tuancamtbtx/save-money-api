package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.core.MessageEntity;
import vn.xteam.savemoneyapi.entities.form.SavingBookForm;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;
import vn.xteam.savemoneyapi.entities.v1.SavingBookReport;
import vn.xteam.savemoneyapi.service.ISavingBookService;

import java.util.List;

@RequestMapping(EndpointConfig.SAVING_BOOK_API)
@CrossOrigin(origins = "*")
@RestController
public class SavingsBookController {
    private final ISavingBookService savingBookService;

    @Autowired
    public SavingsBookController(ISavingBookService savingBookService) {
        this.savingBookService = savingBookService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SavingBookEntity>> getAllUsers() {
        List<SavingBookEntity> list = savingBookService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<MessageEntity> createUser(@RequestBody SavingBookForm body) throws Exception {
        try {
            savingBookService.save(body);
            MessageEntity res = MessageEntity.builder().message("created").status(true).build();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception ex) {
            MessageEntity res = MessageEntity.builder().message(ex.getMessage()).status(false).build();
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/report",produces = "application/json")
    public ResponseEntity<List<SavingBookReport>> report() {
        List<SavingBookReport> list = savingBookService.report();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
