package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
public abstract class PersonDto {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private String area;

    private String city;

    private String address;
}
