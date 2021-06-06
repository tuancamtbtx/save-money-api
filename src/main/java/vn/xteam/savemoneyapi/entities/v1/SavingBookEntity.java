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
public class SavingBookEntity implements IApiBaseEntity {
    private String userId;
    private String id;
    private Long amount;
    private int type;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
