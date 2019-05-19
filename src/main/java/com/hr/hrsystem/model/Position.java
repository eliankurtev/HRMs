package com.hr.hrsystem.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    @Length(max = 8)
    //НКПИД
    private Short jobID;

    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees;

    @ManyToMany(mappedBy = "positions")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department department;

}
