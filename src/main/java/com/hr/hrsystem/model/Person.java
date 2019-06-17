package com.hr.hrsystem.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@MappedSuperclass
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public Person(String firstName, String middleName, String lastName, String gender, String address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
    }
}
