package com.hr.hrsystem.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    private Long id;

    @Column
    @NotNull
    @Email
    private String email;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String photo;

    @Column(name = "vacation_days")
    private Integer vacationDays;

    @Column(name = "job_number")
    @NotNull
    @Length(max = 9)
    private Integer jobNumber;

    // 4/6/8
    @Column(name = "working_hours")
    @NotNull
    private Integer workingHours;

    @Column(name = "working_days")
    @NotNull
    private Integer workingDays;

    @OneToOne
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;

    @OneToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_position",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id") }
    )
    List<Position> positions;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_skill",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    List<Skill> skills;

}
