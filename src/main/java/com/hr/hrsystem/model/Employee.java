package com.hr.hrsystem.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {
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

    @OneToOne
    @JoinColumn(name = "security_data_id")
    private SecurityData securityData;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "employee_position",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id")}
    )
    List<Position> positions;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "employee_skill",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    List<Skill> skills;

    @Builder
    public Employee(String firstName, String middleName, String lastName,
                    String gender, String address,
                    String email, String startDate, Integer vacationDays,
                    Integer workingHours, Integer workingDays){
        super( firstName, middleName, lastName, gender, address);
        this.email = email;
        this.startDate = LocalDate.parse(startDate);
        this.vacationDays = vacationDays;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
    }
}
