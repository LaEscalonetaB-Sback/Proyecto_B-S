package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Person;


import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> list(String nameComplete, String rol, String seniority, String skill);
    Person create(Person person);
    List<Person> getAllPerson();
    Optional<Person> getPersonById(long id);
    Person updatePerson(Person updatePerson);
    void deletePerson(long id);

    boolean existById(Long id) throws Exception;

    Person obtainPersonId(Long id) throws Exception;

    Person update(Person person) throws Exception;

    void delete(Long id) throws Exception;


}
