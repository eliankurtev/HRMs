package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.dto.ContractDto;
import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.model.*;
import com.hr.hrsystem.repository.CompanyRepository;
import com.hr.hrsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class HireEmployeeServiceImpl implements HireEmployeeService {
    @Autowired
    CompanyRepository companyRepository;

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
    public Long hireEmployee(EmployeeDto employeeDto) {
        Person person = Person.builder()
                .address(employeeDto.getAddress())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .lastName(employeeDto.getLastName())
                .gender(employeeDto.getGender())
                .email(employeeDto.getEmail())
                .build();
        Long personId = personService.savePerson(person);

        JobType job = JobType.valueOf(employeeDto.getJobName());
        System.out.println(job.getJobId());

        Company c = companyRepository.findByEik((short) 5555);

        Employee employee = Employee.employeeBuilder()
                .startDate(employeeDto.getStartDate())
                .vacationDays(employeeDto.getVacationDays())
                .workingDays(employeeDto.getWorkingDays())
                .workingHours(employeeDto.getWorkingHours())
                .jobNumber(job)
                .build();
        employee.setCompany(c);
        employee.setPerson(person);

        SecurityData securityData = new SecurityData();
        securityData.setSalary(employeeDto.getSalary());
        securityData.setIBAN(employeeDto.getIban());
        SecurityData save = securityDataService.save(securityData);
        boolean isSavedSecurity = Objects.nonNull(save);

        employee.setSecurityData(securityData);

        List<Skill> skill = skillService.getByName(employeeDto.getSkill());
        if (Objects.nonNull(skill)) {
            employee.setSkills(skill);
        }

        Grade grade = gradeService.findByName(employeeDto.getGrade());
        employee.setGrade(grade);

        Employee saveEmployee = employeeService.saveEmployee(employee);
        Long employeeId = saveEmployee.getId();

        return employeeId;
    }

    @Override
    public ApplicationForVacationDto createApplicationForVacation(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);

        return ApplicationForVacationDto.builder()
                .firstNameEmployee(employee.getPerson().getFirstName())
                .secondNameEmployee(employee.getPerson().getMiddleName())
                .lastNameEmployee(employee.getPerson().getLastName())
                .firmName(employee.getCompany().getName())
                .jobNameEmployee(employee.getJobNumber().toString()).build();
    }

    @Override
    public ContractDto createContract(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);

        return ContractDto.builder()
                .employeeFirstName(employee.getPerson().getFirstName())
                .employeeMiddleName(employee.getPerson().getMiddleName())
                .employeeLastName(employee.getPerson().getLastName())
                .employeeEgn(employee.getSecurityData().getEgn().toString())
                .employeeLkNumber(employee.getSecurityData().getIdNumber().toString())
                .employeeLkDate(employee.getSecurityData().getIssuedDate())
                .employeeLkMvr(employee.getSecurityData().getAuthority())
                .employeeAddress(employee.getPerson().getAddress())
                .employeeJobName(employee.getJobNumber().name())
                .employeeSalary(employee.getSecurityData().getSalary())
                .employeeYearsOfLabour(employee.getSecurityData().getYearsOfLabour().toString())
                .employeeMonthsOfLabour(employee.getSecurityData().getMonthsOfLabour().toString())
                .employeeDaysOfLabour(employee.getSecurityData().getYearsOfLabour().toString())

                .firmName(employee.getCompany().getName())
                .firmCity(employee.getCompany().getCity())
                .firmAddress(employee.getCompany().getAddress())
                .firmEik(employee.getCompany().getEik().toString())
                .hrFirstName(employee.getPerson().getFirstName())
                .hrMiddleName(employee.getPerson().getMiddleName())
                .hrLastName(employee.getPerson().getLastName())
                .hrEgn(employee.getSecurityData().getEgn().toString())
                .build();
    }


    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) {

        Person person = personService.findById(Long.parseLong(employeeDto.getId()));
                person.setAddress(employeeDto.getAddress());
                person.setFirstName(employeeDto.getFirstName());
                person.setMiddleName(employeeDto.getMiddleName());
                person.setLastName(employeeDto.getLastName());
                person.setGender(employeeDto.getGender());
                person.setEmail(employeeDto.getEmail());


        Long personId =  personService.savePerson(person);

        Employee employee = employeeService.findOneById(employeeDto.getId());
                employee.setStartDate(LocalDate.parse(employeeDto.getStartDate()));
                employee.setVacationDays(employeeDto.getVacationDays());
                employee.setWorkingDays(employeeDto.getWorkingDays());
                employee.setWorkingHours(employeeDto.getWorkingHours());
                employee.setJobNumber(JobType.valueOf(employeeDto.getJobName()));

        employee.setPerson(person);

        SecurityData securityData = employee.getSecurityData();
        securityData.setSalary(employeeDto.getSalary());
        SecurityData save = securityDataService.save(securityData);
        boolean isSavedSecurity = Objects.nonNull(save);

        employee.setSecurityData(securityData);

        Grade grade = gradeService.findByName(employeeDto.getGrade());
        employee.setGrade(grade);

        List<Skill> skill = skillService.getByName(employeeDto.getSkill());
        if (Objects.nonNull(skill)) {
            employee.setSkills(skill);
        }



        Employee saveEmployee = employeeService.saveEmployee(employee);
        boolean isSavedEmployee = Objects.nonNull(saveEmployee);

        return isSavedEmployee && personId > 0 && isSavedSecurity;
    }

    @Override
    public void updateEmployees(List<EmployeeDto> employeeDtos) {
        employeeDtos.forEach(this::updateEmployee);
    }

    @Override
    public List<JobType> getJobTypes() {
        return Arrays.asList(JobType.values());
    }
}
