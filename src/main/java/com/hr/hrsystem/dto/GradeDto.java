package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradeDto {
    private Long id;

    private String name;
}
