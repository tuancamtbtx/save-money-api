package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class SavingBookEntity implements IApiBaseEntity {
    private String userId;
    private String id;
    private Long amount;
    private int type;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}