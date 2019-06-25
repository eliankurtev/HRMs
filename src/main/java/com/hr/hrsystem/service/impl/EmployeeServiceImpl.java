package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.model.JobType;
import com.hr.hrsystem.model.Grade;
import com.hr.hrsystem.model.JobType;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.repository.EmployeeRepository;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.GradeService;
import com.hr.hrsystem.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service(value = "userService")
@Slf4j
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private PositionService positionService;

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
    public List<Employee> findByGrade(String gradeName) {
        Grade grade = gradeService.findByName(gradeName);
        return employeeRepository.findAllByGrade(grade);
    }

    @Override
    public List<Employee> findAllByPosition(String position) {
        Position oneByName = positionService.findOneByName(position);
        return employeeRepository.findAllByPositions(Collections.singletonList(oneByName));
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
    public List<Employee> findAllHrs() {
        return employeeRepository.findAllByJobNumber(JobType.HR);
    }

    @Override
    public List<Employee> findAllBenchEmployees() {
        return employeeRepository.findAllByJobNumber(JobType.BENCH);
    }

    @Override
    public Employee fireEmployee(String id) {
        Employee employee = findOneById(id);
        employee.setIsFired(true);
        employee.setEndDate(LocalDate.now());
        return saveEmployee(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        //TODO - HR Type
        if (user.getJobNumber().equals(JobType.JAVA_SOFTWARE_DEVELOPER)) {
            log.info("HR");
            String role = "ADMIN";
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        } else {
            log.info("USER");
            String role = "USER";
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);
    }

}
