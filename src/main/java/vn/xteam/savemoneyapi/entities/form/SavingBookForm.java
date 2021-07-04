package vn.xteam.savemoneyapi.entities.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SavingBookForm implements Serializable {
    @SerializedName("id_card")
    @JsonProperty("id_card")
    private String idCard;

    @SerializedName("type")
    @JsonProperty("type")
    private Long type;

    @SerializedName("amount")
    @JsonProperty("amount")
    private Double amount;
}
