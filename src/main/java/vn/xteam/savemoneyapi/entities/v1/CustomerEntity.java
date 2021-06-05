package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class CustomerEntity {
    private String id;
    private String email;
    private String fullName;
    private boolean active;
    private String avatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
