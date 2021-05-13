package vn.xteam.savemoneyapi.entities.v1;

import java.sql.Timestamp;
import java.util.List;

public class CustomerEntity {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private List<RolePermission> rolePermissions;
    private boolean active;
    private String password;
    private String hashedPassword;
    private String avatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
