package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Person;

import java.util.List;

public interface PersonService {
    Person findById(Long id);

    Long savePerson(Person person);

    List<Person> getAllByNameContaining(String substring);
}
