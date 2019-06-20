package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.dto.PersonDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.model.Skill;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransformationServiceImpl implements TransformationService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public EmployeeDto getById(String id) {
        Employee employee = employeeService.findOneById(id);
        return employeeToDto(employee);
    }


    @Override
    public EmployeeDto employeeToDto(Employee employee) {
        if (Objects.isNull(employee)) {
            return null;
        }

        Person person = employee.getPerson();
        return EmployeeDto.employeeDtoBuilder()
                .id(employee.getId().toString())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .firstName(person.getFirstName())
                .address(person.getAddress())
                .email(employee.getPerson().getEmail())
                .grade(employee.getGrade().getName())
                .salary(employee.getSecurityData().getSalary())
                .workingHours(employee.getWorkingHours())
                .workingDays(employee.getWorkingDays())
                .vacationDays(employee.getVacationDays())
                .startDate(employee.getStartDate().toString())
                .jobId(employee.getJobNumber().getJobId())
                .skill(employee.getSkills().stream().map(Skill::getName).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<EmployeeDto> getEmployeeDtos(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(e -> employeeDtos.add(employeeToDto(e)));

        return employeeDtos;
    }

    @Override
    public List<EmployeeDto> getEmployeeDtos() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(e -> employeeDtos.add(employeeToDto(e)));

        return employeeDtos;
    }
}