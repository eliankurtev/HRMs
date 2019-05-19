package com.hr.hrsystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String middleName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String gender;

    @Column
    private String area;

    @Column
    private String city;

    @Column
    private String address;

    @OneToOne(mappedBy = "person")
    private Employee employee;
}
