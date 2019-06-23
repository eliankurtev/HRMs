package com.hr.hrsystem.resource;

import com.hr.hrsystem.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FireEmployeeController {
@Autowired
private EmployeeService employeeService;

    @RequestMapping(value = "/fire/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> fireEmployee(@PathVariable String id) {
        log.info(id);
        employeeService.fireEmployee(id);
        return null;
    }
}
