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
public class ContractDto {
    private String firmName;
    private String firmCity;
    private String firmAddress;
    private String firmEik;
    private String hrFirstName;
    private String hrMiddleName;
    private String hrLastName;
    private String hrEgn;

    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private String employeeEgn;
    private String employeeLkNumber;
    private LocalDate employeeLkDate;
    private String employeeLkMvr;
    private String employeeAddress;
    private String employeeJobName;
    private Float employeeSalary;
    private String employeeYearsOfLabour;
    private String employeeMonthsOfLabour;
    private String employeeDaysOfLabour;
}
