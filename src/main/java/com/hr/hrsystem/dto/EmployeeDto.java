package com.hr.hrsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends PersonDto {

    private String email;

    @JsonFormat(pattern = "####-##-##")
    private String startDate;

    private LocalDate endDate;

    private Integer vacationDays;

    private Integer jobId;

    private Integer workingHours;

    private Integer workingDays;

    private Float salary;

    private String grade;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonDeserialize
    private List<String> skill;

    private Boolean show;

    @Builder(builderMethodName = "employeeDtoBuilder")
    private EmployeeDto(String id, String firstName, String middleName, String lastName,
                        String gender, String address, Integer jobId,
                        String email, String startDate, Integer vacationDays,
                        Integer workingHours, Integer workingDays, Float salary, String grade, List<String> skill) {
        super(id, firstName, middleName, lastName, gender, address);
        this.email = email;
        this.jobId = jobId;
        this.startDate = startDate;
        this.vacationDays = vacationDays;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
        this.salary = salary;
        this.grade = grade;
        this.skill = skill;
    }

}
