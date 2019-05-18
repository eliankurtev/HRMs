package com.hr.hrsystem.dto;

import java.time.LocalDate;

public class EmployeeDto extends PersonDto {

    private String email;

    private LocalDate starDate;

    private LocalDate endDate;

    private String photo;

    private Integer vacationDays;

    private Integer jobNumber;

    private Integer workingHours;

    private Integer workingDays;
}
