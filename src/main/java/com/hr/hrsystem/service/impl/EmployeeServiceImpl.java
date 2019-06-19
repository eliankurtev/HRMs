package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.repository.EmployeeRepository;
import com.hr.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

   @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findOneById(String id) {
        return employeeRepository.findById(Long.parseLong(id)).isPresent() ? employeeRepository.findById(Long.parseLong(id)).get() : null;
    }
}
