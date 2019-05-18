package com.hr.hrsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;


}
