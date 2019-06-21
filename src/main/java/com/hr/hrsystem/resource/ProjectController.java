package com.hr.hrsystem.resource;

import com.hr.hrsystem.dto.PositionDto;
import com.hr.hrsystem.dto.ProjectDto;
import com.hr.hrsystem.model.Department;
import com.hr.hrsystem.model.Position;
import com.hr.hrsystem.service.DepartmentService;
import com.hr.hrsystem.service.PositionService;
import com.hr.hrsystem.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class ProjectController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findSkills() {
        return new ResponseEntity<>(departmentService.findAllNames(), HttpStatus.OK);
    }


    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findPositions() {
        return new ResponseEntity<>(positionService.findAllNames(), HttpStatus.OK);
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> getGrades(@RequestBody PositionDto positionDto) {
        log.info(positionDto.toString());
        boolean position = Objects.nonNull(positionService.save(positionDto));
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> getProject(@RequestBody ProjectDto projectDto) {
        log.info(projectDto.toString());
        boolean position = Objects.nonNull(projectService.save(projectDto));
        return new ResponseEntity<>(position, HttpStatus.OK);
    }
}
