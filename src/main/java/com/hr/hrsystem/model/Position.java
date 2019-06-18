package com.hr.hrsystem.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Max(value = 8)
    //НКПИД
    private Integer jobid;

    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees;

    @ManyToMany(mappedBy = "positions")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name="department", nullable=false)
    private Department department;

}
