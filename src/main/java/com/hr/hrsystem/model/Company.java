package com.hr.hrsystem.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Length(max = 9)
    private Short eik;

    @Column
    @NotNull
    private String name;

    @Column
    private String area;

    @Column
    private String city;

    @Column
    private String address;



    //TODO: Manager
}
