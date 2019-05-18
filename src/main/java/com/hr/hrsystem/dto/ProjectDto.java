package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder

public class ProjectDto {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;
}
