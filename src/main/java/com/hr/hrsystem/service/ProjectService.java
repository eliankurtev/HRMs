package com.hr.hrsystem.service;

import com.hr.hrsystem.dto.ProjectDto;
import com.hr.hrsystem.model.Project;

public interface ProjectService {
    Project save(ProjectDto projectDto);
}
