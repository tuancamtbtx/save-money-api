package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.web.bind.annotation.RequestMapping;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;

import java.util.List;

@RequestMapping("receipts")
public class ReceiptController implements IController<ReceiptEntity> {
    @Override
    public List<ReceiptEntity> getAll() {
        return null;
    }

    @Override
    public ReceiptEntity create(String id) {
        return null;
    }

    @Override
    public ReceiptEntity getInfo() {
        return null;
    }

    @Override
    public ReceiptEntity updateById(String id) {
        return null;
    }

    @Override
    public void removeById(String id) {

    }
}
