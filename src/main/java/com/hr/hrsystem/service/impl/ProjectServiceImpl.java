package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.ProjectDto;
import com.hr.hrsystem.model.Project;
import com.hr.hrsystem.repository.PositionRepository;
import com.hr.hrsystem.repository.ProjectRepository;
import com.hr.hrsystem.service.ProjectService;
import com.hr.hrsystem.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private TransformationService transformationService;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project save(ProjectDto positionDto){
        return projectRepository.save(transformationService.dtoToProject(positionDto));
    }
}
