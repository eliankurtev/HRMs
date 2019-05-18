package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
