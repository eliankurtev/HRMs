package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.EmployeeDto;
import com.hr.hrsystem.dto.PositionDto;
import com.hr.hrsystem.dto.ProjectDto;
import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.model.Project;

import java.util.List;

public interface TransformationService {
    EmployeeDto getById(String id);

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> getEmployeeDtos(List<Employee> employees);

    List<EmployeeDto> getEmployeeDtos();

    Position dtoToPosition(PositionDto positionDto);

    Project dtoToProject(ProjectDto projectDto);

    List<EmployeeDto> getEmployeeDtosByGrade(String grade);

    List<EmployeeDto> getEmployeeDtosByPosition(String position);

    List<EmployeeDto> getFiredEmployeeDtos();
}
