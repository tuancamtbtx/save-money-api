package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.web.bind.annotation.RequestMapping;
import vn.xteam.savemoneyapi.controllers.business.IController;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;

import java.util.List;

@RequestMapping("saving-books")
public class SavingsBookController implements IController<SavingBookEntity> {
    @Override
    public List<SavingBookEntity> getAll() {
        return null;
    }

    @Override
    public SavingBookEntity create(String id) {
        return null;
    }

    @Override
    public SavingBookEntity getInfo() {
        return null;
    }

    @Override
    public SavingBookEntity updateById(String id) {
        return null;
    }

    @Override
    public void removeById(String id) {

    }
}
