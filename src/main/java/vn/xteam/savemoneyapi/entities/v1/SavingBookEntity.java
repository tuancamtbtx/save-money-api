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
public class SavingBookEntity implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private String id;
    @SerializedName("customer_id")
    @JsonProperty("customer_id")
    private String customerId;

    @SerializedName("customer_name")
    @JsonProperty("customer_name")
    private String customerName;
    @SerializedName("address")
    @JsonProperty("address")
    private String customerAddress;

    @SerializedName("amount")
    @JsonProperty("amount")
    private Long amount;

    @SerializedName("type")
    @JsonProperty("type")
    private int type;

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
