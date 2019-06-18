package com.hr.hrsystem.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class SecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
//    @Length(max = 10)
    private Short egn;

    @Column
//    @Length(max = 9)
    private Short idNumber;

    @Column
    private LocalDate issuedDate;

    @Column
    private String authority;

    @Column
//    @Length(max = 2)
    private Integer yearsOfLabour;

    @Column
//    @Length(max = 2)
    private Integer monthsOfLabour;

    @Column
    private Integer daysOfLabour;

    @Column
    private Float salary;

    @Column
    private String IBAN;

}
