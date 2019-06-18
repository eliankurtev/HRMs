package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Grade;
import com.hr.hrsystem.repository.GradeRepository;
import com.hr.hrsystem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> findAll(){
        return gradeRepository.findAll();
    }

    @Override
    public List<String> findAllNames(){
        return gradeRepository.findAll().stream().map(Grade::getName).collect(Collectors.toList());
    }

    @Override
    public Grade findByName(String name){
        return gradeRepository.findByName(name).isPresent() ? gradeRepository.findByName(name).get() : null;
    }
}
