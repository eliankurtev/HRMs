package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.repository.EmployeeRepository;
import com.hr.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
