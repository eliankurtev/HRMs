package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.service.ExcelService;
import com.hr.hrsystem.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    ExcelService excelService;

    @Autowired
    TransformationService transformationService;

    @RequestMapping("/excel")
    ResponseEntity<Boolean> excel() throws IOException {
        return new ResponseEntity<>(excelService.createExcel(), HttpStatus.OK);
    }

    @GetMapping("/grade/{grade}")
    ResponseEntity<List<EmployeeDto>> getAllByGrades(@PathVariable String grade) throws IOException {
        return new ResponseEntity<>(transformationService.getEmployeeDtosByGrade(grade), HttpStatus.OK);
    }

    @GetMapping("/position/{position}")
    ResponseEntity<List<EmployeeDto>> getAllByPosition(@PathVariable String position)  {
        return new ResponseEntity<>(transformationService.getEmployeeDtosByPosition(position), HttpStatus.OK);
    }
}
