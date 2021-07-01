package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerEntity implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private Long id;

    @SerializedName("full_name")
    @JsonProperty("full_name")
    private String fullName;

    @SerializedName("address")
    @JsonProperty("address")
    private String address;

    @SerializedName("id_card")
    @JsonProperty("id_card")
    private IdentityCardEntity idCard;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;


}
