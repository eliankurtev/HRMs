package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepartmentDto {
    private Long id;

    private String name;
}
