package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Skill;
import com.hr.hrsystem.repository.SkillRepository;
import com.hr.hrsystem.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    @Override
    public List<String> findAllNames(){
        return findAll().stream().map(Skill::getName).collect(Collectors.toList());
    }

    @Override
    public List<Skill> getByName(List<String> name){
        return skillRepository.findAllByNameIn(name);
    }
}
