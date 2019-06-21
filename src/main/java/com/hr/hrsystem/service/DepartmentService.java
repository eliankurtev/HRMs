package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    List<String> findAllNames();

    Department findOneByName(String name);
}
