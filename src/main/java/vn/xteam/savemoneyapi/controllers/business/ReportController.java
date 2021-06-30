package vn.xteam.savemoneyapi.controllers.business;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.config.EndpointConfig;

@RequestMapping(EndpointConfig.REPORT_API)
@CrossOrigin(origins = "*")
@RestController
public class ReportController {
}
