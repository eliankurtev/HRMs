package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ContractDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;

public interface HireEmployeeService {
    boolean hireEmployee(EmployeeDto employeeDto);

    ApplicationForVacationDto createApplicationForVacation(Long employeeId);

    ContractDto createContract(Long employeeId);
}
