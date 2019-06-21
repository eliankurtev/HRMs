package com.hr.hrsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "job_number")
    //НКПИД
    private JobType jobID;

    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees;

    @ManyToMany(mappedBy = "positions")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name="department", nullable=false)
    private Department department;

}
