package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Setter
@Getter
@Builder
@ToString
public class PaySlipEntity {
    private String userId;
    private String accountCreatedId;
    private String savingBookId;
    private Double amount;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
