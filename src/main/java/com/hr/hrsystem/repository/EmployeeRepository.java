package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.Grade;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByIsFiredTrue();
    List<Employee> findAllByIsFiredFalseOrIsFiredIsNull();
    List<Employee> findAllByGrade(Grade grade);
    List<Employee> findAllByPositions(List<Position> positions);

}
