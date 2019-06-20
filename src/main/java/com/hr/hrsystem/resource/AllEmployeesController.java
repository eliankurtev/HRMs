package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.TransformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AllEmployeesController {
    @Autowired
    private TransformationService transformationService;

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
}
