package com.hr.hrsystem.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@EqualsAndHashCode
@NoArgsConstructor
public class Employee {
    @Id
    private Long id;

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
    @Max(value = 9)
    private Integer jobNumber;

    @Column(name = "working_hours")
    @NotNull
    private Integer workingHours;

    @Column(name = "working_days")
    @NotNull
    private Integer workingDays;

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne
    @JoinColumn(name = "id")
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

    @Builder(builderMethodName = "employeeBuilder")
    public Employee(String email, String startDate, Integer vacationDays,
                    Integer workingHours, Integer workingDays) {
        this.startDate = LocalDate.parse(startDate);
        this.vacationDays = vacationDays;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return this.person.toString() +
                this.startDate + this.vacationDays
                + this.workingDays + this.workingHours;
    }
}
