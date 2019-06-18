package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByNameIn(List<String> name);
}
