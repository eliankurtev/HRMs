package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GradeService {
    List<Grade> findAll();

    List<String> findAllNames();
}
