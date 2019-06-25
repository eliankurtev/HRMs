package com.hr.hrsystem.resource;

import com.hr.hrsystem.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Person> listUser() {
        return null;
    }


    @RequestMapping("/hello")
    ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

}
