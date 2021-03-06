package com.hr.hrsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
