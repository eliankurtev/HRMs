package com.hr.hrsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SecurityDataDto {

    private Long id;

    private Short egn;

    private Integer idNumber;

    private LocalDate issuedDate;

    private String authority;

    private Integer yearsOfLabour;

    private Integer monthsOfLabour;

    private Integer daysOfLabour;

    private Double salary;

    private String IBAN;
}
