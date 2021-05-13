package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.EventLogEntity;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;

public interface IEventService {
    void save(EventLogEntity event);

}
