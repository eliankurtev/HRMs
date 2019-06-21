package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.EmployeeDto;

import java.util.List;

public interface HireEmployeeService {
    boolean hireEmployee(EmployeeDto employeeDto);

    boolean updateEmployee(EmployeeDto employeeDto);

    void updateEmployees(List<EmployeeDto> employeeDtos);
}
