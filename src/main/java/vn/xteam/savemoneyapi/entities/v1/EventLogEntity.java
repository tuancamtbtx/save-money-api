package vn.xteam.savemoneyapi.entities.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EventLogEntity {
    private  String id;
    private int eventType;
    private int eventName;
    private String description;
}
