package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.TransactionDao;
import vn.xteam.savemoneyapi.entities.v1.TransactionReport;
import vn.xteam.savemoneyapi.service.ITransactionService;

import java.util.List;

@Service

public class TransactionServiceImpl implements ITransactionService {
    private final TransactionDao dao;

    @Autowired
    public TransactionServiceImpl(TransactionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<TransactionReport> report() {
        return dao.report();
    }
}
