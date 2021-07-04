package vn.xteam.savemoneyapi.entities.v1;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SavingBookReport implements Serializable {
    Date date;
    int total;
    int status;
}
