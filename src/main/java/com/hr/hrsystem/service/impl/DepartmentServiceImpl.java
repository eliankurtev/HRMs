package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Department;
import com.hr.hrsystem.repository.DepartmentRepository;
import com.hr.hrsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<String> findAllNames() {
        return findAll().stream().map(Department::getName).collect(Collectors.toList());
    }

    @Override
    public Department findOneByName(String name) {
        return departmentRepository.findByName(name).isPresent() ? departmentRepository.findByName(name).get() : null;
    }
}
