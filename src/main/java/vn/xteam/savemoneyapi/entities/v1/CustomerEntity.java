package vn.xteam.savemoneyapi.entities.v1;

import java.util.List;

public class CustomerEntity {
    private String id;
    private String userName;
    private String email;
    private String fullName;
    private List<RolePermission> rolePermissions;
    private boolean active;
    private String password;
    private String hashedPassword;
    private String avatar;


}
