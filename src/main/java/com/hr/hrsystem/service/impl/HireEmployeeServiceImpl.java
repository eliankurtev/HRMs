package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.*;
import com.hr.hrsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HireEmployeeServiceImpl implements HireEmployeeService {
    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityDataService securityDataService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SkillService skillService;

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

        List<Skill> skill = skillService.getByName(employeeDto.getSkill());
        if(Objects.nonNull(skill)){
            employee.setSkills(skill);
        }

        Grade grade = gradeService.findByName(employeeDto.getGrade());
        employee.setGrade(grade);

        Employee saveEmployee = employeeService.saveEmployee(employee);
        boolean isSavedEmployee = Objects.nonNull(saveEmployee);

        return isSavedEmployee && isSavedPerson && isSavedSecurity;
    }

    @Override
    public ApplicationForVacationDto createApplicationForVacation(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);

        return ApplicationForVacationDto.builder()
                .firstNameEmployee(employee.getPerson().getFirstName())
                .secondNameEmployee(employee.getPerson().getMiddleName())
                .lastNameEmployee(employee.getPerson().getLastName())
                .jobNameEmployee(employee.getJobNumber().toString())
                .firmName(employee.getCompany().getName())
                .build();
    }
}
