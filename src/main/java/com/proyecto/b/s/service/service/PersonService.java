package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;


import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PersonService {

    Person create(PersonRequestDTO personRequestDto) throws Exception;

<<<<<<< HEAD
    List<PersonResponseDto> search(String name, String lastName, List<String> seniorityGeneral, List<String> roles, List<String> skills);
=======
    List<PersonResponseDTO> search(String name, String lastName, String seniorityGeneral, List<String> roles, List<String> skills);
>>>>>>> ee8e20ca6786360970fbbf1102b0a63dc46ec98a

    boolean existById(Long id) throws Exception;

    Person obtainPersonId(Long id) throws Exception;


    PersonResponseDTO update(Long Id, PersonUpdateRequestDTO personRequestDto) throws EntityNotFoundException;

    void delete(Long id) throws Exception;


}
