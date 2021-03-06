package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ContractDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.JobType;

import java.util.List;

public interface HireEmployeeService {
    Long hireEmployee(EmployeeDto employeeDto);

    boolean updateEmployee(EmployeeDto employeeDto);

    void updateEmployees(List<EmployeeDto> employeeDtos);

    ApplicationForVacationDto createApplicationForVacation(Long employeeId);

    ContractDto createContract(Long employeeId);

    List<JobType> getJobTypes();
}
