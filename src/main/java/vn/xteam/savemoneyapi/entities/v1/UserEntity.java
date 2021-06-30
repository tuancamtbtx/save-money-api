package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserEntity implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private String id;

    @SerializedName("user_name")
    @JsonProperty("user_name")
    private String userName;
    @SerializedName("full_name")
    @JsonProperty("full_name")
    private String fullName;
    @SerializedName("email")
    @JsonProperty("email")
    private String email;

    private String password;
    private String hashPassword;
    @SerializedName("token")
    @JsonProperty("token")
    private String token;
    @SerializedName("created_by")
    @JsonProperty("created_by")
    private String createdBy;
    @SerializedName("updated_by")
    @JsonProperty("updated_by")
    private String updatedBy;
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

}
