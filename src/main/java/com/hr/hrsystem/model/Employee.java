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
public class Employee {
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
//    @NotNull
    @Length(max = 9)
    private Integer jobNumber;

    // 4/6/8
    @Column
//    @NotNull
    private Integer workingHours;

    @Column
//    @NotNull
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
            name = "employee_skills",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    List<Skill> skills;

}
