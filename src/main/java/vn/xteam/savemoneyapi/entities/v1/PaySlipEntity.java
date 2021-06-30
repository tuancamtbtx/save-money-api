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
public class PaySlipEntity  implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private String id;
    @SerializedName("saving_book_code")
    @JsonProperty("saving_book_code")
    private String savingBookCode;

    @SerializedName("saving_book_type")
    @JsonProperty("saving_book_type")
    private String savingBookType;

    @SerializedName("customer_code")
    @JsonProperty("customer_code")
    private String customerCode;

    @SerializedName("customer_name")
    @JsonProperty("customer_name")
    private String customerName;

    @SerializedName("amount")
    @JsonProperty("amount")
    private Double amount;

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
