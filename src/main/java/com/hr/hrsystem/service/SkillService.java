package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();

    List<String> findAllNames();
}
