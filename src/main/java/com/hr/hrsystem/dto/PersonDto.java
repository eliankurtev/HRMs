package com.hr.hrsystem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PersonDto {

    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private String area;

    private String city;

    private String address;

    @Builder
    public PersonDto(String id, String firstName, String middleName, String lastName,
                     String gender, String address){
        this.id = id;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
