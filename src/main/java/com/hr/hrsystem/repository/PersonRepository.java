package com.hr.hrsystem.repository;

import com.hr.hrsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> getAllByFirstNameContaining(String substring);
}
