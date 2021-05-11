package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class UserEntity implements IApiBaseEntity{
    private String id;
    private String username;
    private String email;
    private String password;
    private String hashPassword;
    private String token;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + username  + ", email=" + email + "]";
    }
}
