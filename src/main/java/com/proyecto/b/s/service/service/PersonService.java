package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.personRequestDTO.PersonRequestDTO;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;


import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PersonService {

    PersonResponseDTO create(PersonRequestDTO personRequestDto) throws Exception;


    List<PersonResponseDTO> search(String name, String lastName, List<String> seniorityGeneral, List<String> roles, List<String> skills);

    boolean existById(Long id) throws Exception;

    Person obtainPersonId(Long id) throws Exception;
    PersonResponseDTO update(Long Id, PersonUpdateRequestDTO personRequestDto) throws EntityNotFoundException, Exception;

    void delete(Long id) throws Exception;

}
