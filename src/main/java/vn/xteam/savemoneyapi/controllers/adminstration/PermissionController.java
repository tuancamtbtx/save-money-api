package vn.xteam.savemoneyapi.controllers.adminstration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.xteam.savemoneyapi.config.EndpointConfig;
import vn.xteam.savemoneyapi.entities.v1.PermissionEntity;
import vn.xteam.savemoneyapi.service.IPermissionService;

import java.util.List;

@Slf4j
@RequestMapping(EndpointConfig.PERMISSION_API)
@RestController
public class PermissionController {
    private final IPermissionService permissionService;

    @Autowired
    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<PermissionEntity>> getList(@RequestHeader("authorization") String token) {
        List<PermissionEntity> list = permissionService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
