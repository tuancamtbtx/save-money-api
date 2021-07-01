package vn.xteam.savemoneyapi.entities.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EventLogEntity {
    private  Long id;
    private int eventType;
    private int eventName;
    private String userId;
    private String description;

}
