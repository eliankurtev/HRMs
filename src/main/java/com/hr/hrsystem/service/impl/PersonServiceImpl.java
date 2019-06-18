package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.repository.PersonRepository;
import com.hr.hrsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person){
        return personRepository.save(person);
    }
}
