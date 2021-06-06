package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerEntity {
    private String id;
    private String email;
    private String fullName;
    private boolean active;
    private String avatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
