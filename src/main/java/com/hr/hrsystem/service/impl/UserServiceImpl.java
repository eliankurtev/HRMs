package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Person;
import com.hr.hrsystem.repository.PersonRepository;
import com.hr.hrsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonRepository personRepository;

//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        Optional<Person> userOpt = personRepository.findByUsername(userId);
//        if(!userOpt.isPresent()){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(userOpt.get(), userOpt.get(), getAuthority());
//    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Person findOne(long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person save(Person user) {
        return personRepository.save(user);
    }
}
