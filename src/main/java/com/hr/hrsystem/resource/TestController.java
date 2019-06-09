package com.hr.hrsystem.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
