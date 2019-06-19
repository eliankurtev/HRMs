package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO HIERARCHY
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
