package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ContractDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;

import java.util.List;

public interface HireEmployeeService {
    boolean hireEmployee(EmployeeDto employeeDto);

    boolean updateEmployee(EmployeeDto employeeDto);

    void updateEmployees(List<EmployeeDto> employeeDtos);

    ApplicationForVacationDto createApplicationForVacation(Long employeeId);

    ContractDto createContract(Long employeeId);
}
