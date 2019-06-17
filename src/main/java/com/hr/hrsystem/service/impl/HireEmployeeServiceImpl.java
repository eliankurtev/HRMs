package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.HireEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HireEmployeeServiceImpl implements HireEmployeeService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean hireEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee.builder()
                .address(employeeDto.getAddress())
                .email(employeeDto.getEmail())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .lastName(employeeDto.getLastName())
                .gender(employeeDto.getGender())
                .startDate(employeeDto.getStartDate())
                .vacationDays(employeeDto.getVacationDays())
                .workingDays(employeeDto.getWorkingDays())
                .workingHours(employeeDto.getWorkingHours())
                .build();

        return Objects.isNull(employeeService.saveEmployee(employee));
    }
}
