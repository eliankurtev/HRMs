package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.repository.EmployeeRepository;
import com.hr.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Employee findById(Long id) {
        return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;
    }

   @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByIsFiredFalseOrIsFiredIsNull();
    }

    @Override
    public List<Employee> findAllFired() {
        return employeeRepository.findAllByIsFiredTrue();
    }

    @Override
    public Employee findOneById(String id) {
        return employeeRepository.findById(Long.parseLong(id)).isPresent() ? employeeRepository.findById(Long.parseLong(id)).get() : null;
    }

    @Override
    public Employee fireEmployee(String id) {
        Employee employee = findOneById(id);
        employee.setIsFired(true);
        employee.setEndDate(LocalDate.now());
        return saveEmployee(employee);
    }
}
