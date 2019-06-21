package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Person;

public interface PersonService {
    Person findById(Long id);

    boolean savePerson(Person person);
}
