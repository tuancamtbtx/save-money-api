package vn.xteam.savemoneyapi.entities.v1;

import java.sql.Timestamp;

public class TransactionEntity implements IApiBaseEntity{
    private String customerId;
    private String accountCreatedId;
    private String savingBookId;
    private int type; // 1 : nop tien , 2 : rut tien
    private Double amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
