package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Employee findById(Long id);
}
