package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.TransactionReport;

import java.util.List;

public interface ITransactionService {
    public List<TransactionReport> report();
}
