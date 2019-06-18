package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> findAll();
}
