package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionEntity implements IApiBaseEntity{
    private String customerId;
    private String accountCreatedId;
    private String savingBookId;
    private int type; // 1 : nop tien , 2 : rut tien
    private Double amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
