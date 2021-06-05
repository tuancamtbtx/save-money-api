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
public class PermissionEntity {
    private String id;
    private String name;
    private String slug;
    private int active;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
