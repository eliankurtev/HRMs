package com.hr.hrsystem.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class SecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Length(max = 10)
    private Short egn;

    @Column
    @Length(max = 9)
    private Short idNumber;

    @Column
    private LocalDate issuedDate;

    @Column
    private String authority;

    @Column
    @Length(max = 2)
    private Integer yearsOfLabour;

    @Column
    @Length(max = 2)
    private Integer monthsOfLabour;

    @Column
    @Length(max = 2)
    private Integer daysOfLabour;

    @Column
    private Double salary;

    @Column
    private String IBAN;

}
