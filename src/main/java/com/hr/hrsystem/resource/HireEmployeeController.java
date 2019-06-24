package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.JobType;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.service.GradeService;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.PdfCreatorService;
import com.hr.hrsystem.service.SkillService;
import com.hr.hrsystem.service.PersonService;
import com.hr.hrsystem.service.impl.PdfCreatorServiceImpl;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ResponseEntity<List<JobType>> findJobs() {
        return new ResponseEntity<>(hireEmployeeService.getJobTypes(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hire", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> hireEmployee(@RequestBody EmployeeDto testDTO) {
        log.info(testDTO.toString());
        boolean hireEmployee = hireEmployeeService.hireEmployee(testDTO);
        return new ResponseEntity<>(hireEmployee, HttpStatus.OK);
    }

    @RequestMapping("/createContract")
    @ResponseBody
    String homeContract() {
        try {
            Employee hr = null;
            createContract(1L, hireEmployeeService);
            return "PDF Created!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error in creating pdf: ";
        }
    }

    private void createContract(Long id, HireEmployeeService hireEmployeeService) {
        PdfCreatorServiceImpl pdfCreatorService = new PdfCreatorServiceImpl();
        try {
            pdfCreatorService.createContract(id, hireEmployeeService);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> addPerson(@RequestBody Person testDTO) {
        log.info(testDTO.toString());
        boolean addPerson = personService.savePerson(testDTO);
        return new ResponseEntity<>(addPerson, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateEmployeeJob", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> updateEmployee(@RequestBody EmployeeDto employee) {
        boolean addPerson = hireEmployeeService.updateEmployee(employee);
        return new ResponseEntity<>(addPerson, HttpStatus.OK);
    }
}
