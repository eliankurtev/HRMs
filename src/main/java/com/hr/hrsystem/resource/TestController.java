package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.service.ExcelService;
import com.hr.hrsystem.service.GradeService;
import com.hr.hrsystem.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
