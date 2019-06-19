package com.hr.hrsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

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

    private Float salary;

    private String grade;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> skill;

    @Builder(builderMethodName = "employeeDtoBuilder")
    private EmployeeDto(String id, String firstName, String middleName, String lastName,
                        String gender, String address,
                        String email, String startDate, Integer vacationDays,
                        Integer workingHours, Integer workingDays, Float salary, String grade, List<String> skill) {
        super(id, firstName, middleName, lastName, gender, address);
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
