package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<Person> listarPersonas() {
        return null;
    }

    @Override
    public Person guardarPersona(Person persona) {
        return null;
    }

    @Override
    public Person actualizarPersona(Person persona) {
        return null;
    }

    @Override
    public void eliminarPersona(Long id) {

    }
}
