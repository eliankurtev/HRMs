package com.hr.hrsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EmailDto {
    private String email;
    private String interviewDate;
    private String name;
}
