package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
