package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.service.GradeService;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.SkillService;
import com.hr.hrsystem.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HireEmployeeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private HireEmployeeService hireEmployeeService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findGrades() {
        return new ResponseEntity<>(gradeService.findAllNames(), HttpStatus.OK);
    }

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findSkills() {
        return new ResponseEntity<>(skillService.findAllNames(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hire", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> hireEmployee(@RequestBody EmployeeDto testDTO) {
        log.info(testDTO.toString());
        boolean hireEmployee = hireEmployeeService.hireEmployee(testDTO);
        return new ResponseEntity<>(hireEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> addPerson(@RequestBody Person testDTO) {
        log.info(testDTO.toString());
        boolean addPerson = personService.savePerson(testDTO);
        return new ResponseEntity<>(addPerson, HttpStatus.OK);
    }

}
