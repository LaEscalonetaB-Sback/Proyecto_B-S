package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> listarPersonas();
    Person guardarPersona(Person persona);
    Person actualizarPersona(Person persona);
    void eliminarPersona(Long id);
}
