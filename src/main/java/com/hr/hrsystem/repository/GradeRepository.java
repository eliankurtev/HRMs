package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
        Optional<Grade> findByName(String name);
}
