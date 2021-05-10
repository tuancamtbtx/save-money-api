package vn.xteam.savemoneyapi.entities.core;

import java.util.List;

public class ResponseEntity<T> {
    private boolean status;
    private String message;
    private List<T> data;
}
