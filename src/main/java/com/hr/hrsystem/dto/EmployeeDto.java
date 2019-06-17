package com.hr.hrsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends PersonDto {

    private String email;

    private String startDate;

    private LocalDate endDate;

    private Integer vacationDays;

    private Integer jobNumber;

    private Integer workingHours;

    private Integer workingDays;

    private Integer salary;

    private String grade;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private String[] skill;

    @Builder
    private EmployeeDto(Long id, String firstName, String middleName, String lastName,
                        String gender, String area, String city, String address,
                        String email, String startDate, Integer vacationDays,
                        Integer workingHours, Integer workingDays, Integer salary, String grade, String[] skill) {
        super(id, firstName, middleName, lastName, gender, area, city, address);
        this.email = email;
        this.startDate = startDate;
        this.vacationDays = vacationDays;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
        this.salary = salary;
        this.grade = grade;
        this.skill = skill;
    }

}
