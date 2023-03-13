package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.Persona;

import java.util.List;

public interface PersonService {

    List<Person> list(String nameComplete, String rol, String seniority, String skill);
    Person create(Person person) throws Exception;

    boolean existById(Long id) throws Exception;

    Person obtainPersonId(Long id) throws Exception;

    Person update(Person person) throws Exception;


    void delete(Long id) throws Exception;


}
