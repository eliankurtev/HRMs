package com.hr.hrsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApplicationForVacationFEDto {
    private String vacationDays;
    private LocalDate startDate;
}
