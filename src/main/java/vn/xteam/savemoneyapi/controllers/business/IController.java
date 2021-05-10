package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface IController<T> {
    @GetMapping(path = "/", produces = "application/json")
    public List<T> getAll();

    @PostMapping(path = "/}", produces = "application/json")
    public T create(String id);

    @GetMapping(path = "/{id}", produces = "application/json")
    public T getInfo();

    @PutMapping(path = "/{id}", produces = "application/json")
    public T updateById(String id);

    @GetMapping(path = "/{id}", produces = "application/json")
    public void removeById(String id);
}
