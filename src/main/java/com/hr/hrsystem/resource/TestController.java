package com.hr.hrsystem.resource;

import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.service.GradeService;
import com.hr.hrsystem.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Person> listUser() {
        return null;
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findGrades() {
        return new ResponseEntity<>(gradeService.findAllNames(), HttpStatus.OK);
    }


    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findSkills() {
        return new ResponseEntity<>(skillService.findAllNames(), HttpStatus.OK);
    }

    @RequestMapping("/hello")
    ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
