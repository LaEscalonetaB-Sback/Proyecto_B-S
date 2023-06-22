package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.StatePerson;

import java.util.List;

public interface StatePersonService {
    StatePerson save(StatePerson statePerson);
    List<StatePerson> list();
    PersonResponseDTO updatePersonActiveStatus(Long id, String stateName);
}
