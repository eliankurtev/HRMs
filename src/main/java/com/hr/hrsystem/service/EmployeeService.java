package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee findById(Long id);

    void deleteEmployee(Employee employee);

    List<Employee> findAll();


    List<Employee> findByGrade(String grade);

    List<Employee> findAllByPosition(String position);

    List<Employee> findAllFired();

    Employee findOneById(String id);

    List<Employee> findAllHrs();

    Employee fireEmployee(String id);

    List<Employee> findAllBenchEmployees();
}
