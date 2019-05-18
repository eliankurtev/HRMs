package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionDto {
    private Long id;
    private String name;
    //НКПИД
    private Short jobID;
}
