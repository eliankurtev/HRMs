package com.hr.hrsystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "middle_name")
    @NotNull
    private String middleName;

    @Column(name = "last_name")
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

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(mappedBy = "person")
    private Employee employee;
}
