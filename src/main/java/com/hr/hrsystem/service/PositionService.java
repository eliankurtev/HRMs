package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.PositionDto;
import com.hr.hrsystem.model.Position;

import java.util.List;

public interface PositionService {
    Position save(PositionDto positionDto);

    List<Position> findAll();

    List<String> findAllNames();

    Position findOneByName(String name);

    List<Position> findAllByName(List<String> names);
}
