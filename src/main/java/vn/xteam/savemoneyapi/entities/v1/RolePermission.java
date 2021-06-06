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
public class RolePermission {
    private String roleId;
    private String permissionId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
