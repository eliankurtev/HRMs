package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface EmployeeService {




    Employee saveEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    List<Employee> findAll();


    Employee findOneById(String id);
}
