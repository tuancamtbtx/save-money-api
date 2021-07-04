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
public class TransactionEntity implements Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    private Long id;
    @SerializedName("customer_id")
    @JsonProperty("customer_id")
    private Long customerId;
    @SerializedName("customer_id")
    @JsonProperty("customer_id")
    private Long savingBookId;
    @SerializedName("rule_id")
    @JsonProperty("rule_id")
    private Long ruleId;
    @SerializedName("credit_money")
    @JsonProperty("credit_money")
    private Double creditMoney;

    @SerializedName("debit_money")
    @JsonProperty("debit_money")
    private Double debitMoney;

    @SerializedName("type")
    @JsonProperty("type")
    private int type;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}
