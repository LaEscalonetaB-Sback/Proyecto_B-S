package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.entity.Person;


import java.util.List;

public interface PersonService {
    List<PersonResponseDto> list(String nameComplete, String rol, String seniority, String skill);
    Person create(PersonRequestDto personRequestDto) throws Exception;
    List<PersonResponseDto> mapping(List<Person> people);
    boolean existById(Long id) throws Exception;
    Person obtainPersonId(Long id) throws Exception;
    Person update(Person person) throws Exception;
    void delete(Long id) throws Exception;
}
