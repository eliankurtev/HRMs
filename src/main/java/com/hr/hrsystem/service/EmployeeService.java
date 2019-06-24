package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Employee;

import java.util.List;
import com.hr.hrsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface EmployeeService {




    Employee saveEmployee(Employee employee);

    Employee findById(Long id);

    void deleteEmployee(Employee employee);

    List<Employee> findAll();


    List<Employee> findAllFired();

    Employee findOneById(String id);

    List<Employee> findAllHrs();

    Employee fireEmployee(String id);
}
