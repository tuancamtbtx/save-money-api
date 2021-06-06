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
public class RoleEntity {
    private String id;
    private String name;
    private String slug;
    private int active;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
