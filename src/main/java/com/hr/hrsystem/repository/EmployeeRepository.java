package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.Grade;
import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.model.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByIsFiredTrue();
    List<Employee> findAllByIsFiredFalse();
    List<Employee> findAllByJobNumber(JobType jobType);
    List<Employee> findAllByIsFiredFalseOrIsFiredIsNull();
    List<Employee> findAllByGrade(Grade grade);
    List<Employee> findAllByPositions(List<Position> positions);
    Employee findByUsername(String username);
}
