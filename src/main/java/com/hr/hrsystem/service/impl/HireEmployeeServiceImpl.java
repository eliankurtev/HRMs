package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.model.SecurityData;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.PersonService;
import com.hr.hrsystem.service.SecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HireEmployeeServiceImpl implements HireEmployeeService {
    @Autowired
    private PersonService personService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SecurityDataService securityDataService;

    @Override
    public boolean hireEmployee(EmployeeDto employeeDto) {
        Person person = Person.builder()
                .address(employeeDto.getAddress())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .lastName(employeeDto.getLastName())
                .gender(employeeDto.getGender())
                .build();
        Person savePerson = personService.savePerson(person);
        boolean isSavedPerson = Objects.nonNull(savePerson);

        Employee employee = Employee.employeeBuilder()
                .email(employeeDto.getEmail())
                .startDate(employeeDto.getStartDate())
                .vacationDays(employeeDto.getVacationDays())
                .workingDays(employeeDto.getWorkingDays())
                .workingHours(employeeDto.getWorkingHours())
                .build();

        employee.setPerson(person);

        SecurityData securityData = new SecurityData();
        securityData.setSalary(employeeDto.getSalary());
        SecurityData save = securityDataService.save(securityData);
        boolean isSavedSecurity = Objects.nonNull(save);

        employee.setSecurityData(securityData);
        Employee saveEmployee = employeeService.saveEmployee(employee);
        boolean isSavedEmployee = Objects.nonNull(saveEmployee);

        return isSavedEmployee && isSavedPerson && isSavedSecurity;
    }
}
