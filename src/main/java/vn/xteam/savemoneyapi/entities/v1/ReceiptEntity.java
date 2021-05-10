package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class ReceiptEntity {
    private String userId;
    private String accountCreatedId;
    private String savingBookId;
    private Double amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
