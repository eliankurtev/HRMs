package com.hr.hrsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


}
