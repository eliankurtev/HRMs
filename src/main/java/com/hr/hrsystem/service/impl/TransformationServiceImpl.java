package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.dto.PersonDto;
import com.hr.hrsystem.dto.PositionDto;
import com.hr.hrsystem.dto.ProjectDto;
import com.hr.hrsystem.model.*;
import com.hr.hrsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransformationServiceImpl implements TransformationService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private GradeService gradeService;

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

    @Override
    public List<EmployeeDto> getFiredEmployeeDtos() {
        List<Employee> employees = employeeService.findAllFired();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(e -> employeeDtos.add(employeeToDto(e)));

        return employeeDtos;
    }

    @Override
    public Position dtoToPosition(PositionDto positionDto){
        return Position.builder()
                .name(positionDto.getName())
                .department(departmentService.findOneByName(positionDto.getDepartment()))
                .jobID(JobType.findByJobId(positionDto.getJobId()))
                .build();
    }

    @Override
    public Project dtoToProject(ProjectDto projectDto){
        return Project.builder()
                .name(projectDto.getName())
                .positions(positionService.findAllByName(projectDto.getPositions()))
                .grades(gradeService.findAllByName(projectDto.getGrades()))
                .startDate(LocalDate.parse(projectDto.getStartingDate()))
                .skills(skillService.getByName(projectDto.getSkills()))
                .department(departmentService.findOneByName(projectDto.getDepartment()))
                .build();
    }

    public List<EmployeeDto> getHrEmployeeDtos() {
        List<Employee> employees = employeeService.findAllHrs();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(e -> employeeDtos.add(employeeToDto(e)));

        return employeeDtos;
    }

}
