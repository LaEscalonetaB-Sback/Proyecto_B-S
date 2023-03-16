package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.service.service.PersonService;
import com.proyecto.b.s.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<PersonResponseDto> list(String nameComplete, String rol, String seniority, String skill) {
        return null;
    }

    @Override
    public Person create(PersonRequestDto personRequestDto) throws Exception {
        return null;
    }

    @Override
    public List<PersonResponseDto> mapping(List<Person> people) {
        return null;
    }

    @Override
    public boolean existById(Long id) throws Exception {
        return false;
    }

    @Override
    public Person obtainPersonId(Long id) throws Exception {
        return null;
    }

    @Override
    public Person update(Person person) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
