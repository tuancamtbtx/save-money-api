package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Setter
@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class IdentityCardEntity  {
    private Long id;
    private String code;
    private Long customerId;
    private Timestamp providedAt;
}
