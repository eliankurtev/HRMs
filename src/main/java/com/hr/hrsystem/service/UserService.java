package com.hr.hrsystem.service;

import com.hr.hrsystem.model.Person;

import java.util.List;

public interface UserService {
    List<Person> findAll();

    Person findOne(long id);

    void delete(long id);

    Person save(Person user);
}
