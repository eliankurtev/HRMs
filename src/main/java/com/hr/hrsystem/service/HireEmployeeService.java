package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.EmployeeDto;

public interface HireEmployeeService {
    boolean hireEmployee(EmployeeDto employeeDto);

    ApplicationForVacationDto createApplicationForVacation(Long employeeId);
}
