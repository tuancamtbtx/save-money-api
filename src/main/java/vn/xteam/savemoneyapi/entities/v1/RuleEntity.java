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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RuleEntity implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private Long id;

    @SerializedName("name")
    @JsonProperty("name")
    private String name;

    @SerializedName("interest_rate")
    @JsonProperty("interest_rate")
    private float interestRate;

    @SerializedName("min_amount")
    @JsonProperty("min_amount")
    private Double minAmount;

    @SerializedName("period")
    @JsonProperty("period")
    private Integer period;

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
