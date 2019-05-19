package com.hr.hrsystem.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Employee {
    //TODO Hierarchy id - remember
    @Id
    private Long id;

    @Column
    @NotNull
    @Email
    private String email;

    @Column
    private LocalDate starDate;

    @Column
    private LocalDate endDate;

    @Column
    private String photo;

    @Column
    private Integer vacationDays;

    @Column
    @NotNull
    @Length(max = 8)
    private Integer jobNumber;

    // 4/6/8
    @Column
    @NotNull
    private Integer workingHours;

    @Column
    @NotNull
    private Integer workingDays;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    //TODO: Make the fk relations
    // Position
    // Skills

}
