package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserEntity implements IApiBaseEntity{
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String hashPassword;
    private String token;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + username  + ", email=" + email + "]";
    }
}
