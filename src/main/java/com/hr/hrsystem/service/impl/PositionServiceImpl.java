package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.PositionDto;
import com.hr.hrsystem.model.Department;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.repository.PositionRepository;
import com.hr.hrsystem.service.PositionService;
import com.hr.hrsystem.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TransformationService transformationService;

    @Override
    public Position save(PositionDto positionDto){
        return positionRepository.save(transformationService.dtoToPosition(positionDto));
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public List<String> findAllNames() {
        return findAll().stream().map(Position::getName).collect(Collectors.toList());
    }

    @Override
    public Position findOneByName(String name) {
        return positionRepository.findByName(name).isPresent() ? positionRepository.findByName(name).get() : null;
    }

    @Override
    public List<Position> findAllByName(List<String> names){
        return names.stream().map(this::findOneByName).collect(Collectors.toList());
    }
}
