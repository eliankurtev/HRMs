package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByIsFiredTrue();
    List<Employee> findAllByIsFiredFalseOrIsFiredIsNull();
}
