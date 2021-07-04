package vn.xteam.savemoneyapi.entities.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@Builder
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransactionReport implements Serializable {
    @SerializedName("name")
    @JsonProperty("name")
    private String name;
    @SerializedName("debit_money")
    @JsonProperty("debit_money")
    private Double debitMoney;
    @SerializedName("credit_money")
    @JsonProperty("credit_money")
    private Double creditMoney;
    @SerializedName("diff_money")
    @JsonProperty("diff_money")
    private Double diffMoney;
}
