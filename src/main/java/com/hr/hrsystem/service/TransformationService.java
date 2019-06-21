package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;

import java.util.List;

public interface TransformationService {
    EmployeeDto getById(String id);

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> getEmployeeDtos(List<Employee> employees);

    List<EmployeeDto> getEmployeeDtos();

    List<EmployeeDto> getFiredEmployeeDtos();
}
