package com.hr.hrsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "project_grade",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "grade_id") }
    )
    List<Grade> grades;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "project_position",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id") }
    )
    List<Position> positions;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "project_skill",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    List<Skill> skills;

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department department;
}
