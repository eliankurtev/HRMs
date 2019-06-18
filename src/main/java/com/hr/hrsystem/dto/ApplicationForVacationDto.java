package com.hr.hrsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApplicationForVacationDto {
    private String firstNameEmployee;
    private String secondNameEmployee;
    private String lastNameEmployee;
    private String jobNameEmployee;
    private String firmName;
}
