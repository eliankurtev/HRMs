package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.TransformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AllEmployeesController {
    @Autowired
    private TransformationService transformationService;

    @Autowired
    private HireEmployeeService hireEmployeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> findEmployees() {
        log.info(transformationService.getEmployeeDtos().toString());
        return new ResponseEntity<>(transformationService.getEmployeeDtos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String id){
        log.info(transformationService.getById(id).toString());
        return new ResponseEntity<>(transformationService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<?> updateEmployees(@RequestBody List<EmployeeDto> employeeDtoList) {
        hireEmployeeService.updateEmployees(employeeDtoList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getArchive() {
        List<EmployeeDto> employees = transformationService.getFiredEmployeeDtos();
        log.info(employees.toString());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
