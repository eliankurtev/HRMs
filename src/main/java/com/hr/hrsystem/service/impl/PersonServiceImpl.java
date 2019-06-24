package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.repository.PersonRepository;
import com.hr.hrsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean savePerson(Person person){
        return Objects.nonNull(personRepository.save(person));
    }

    @Override
    public Person findById(Long id){
        return personRepository.findById(id).isPresent() ? personRepository.findById(id).get() : null;
    }

    @Override
    public List<Person> getAllByNameContaining(String substring) {
        return personRepository.getAllByFirstNameContaining(substring);
    }
}
