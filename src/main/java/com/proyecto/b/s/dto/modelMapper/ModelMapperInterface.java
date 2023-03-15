package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.entity.Person;

public interface ModelMapperInterface {

    Person personReqDtoToPerson(PersonRequestDto personRequestDto);

    PersonResponseDto personToPersonResponseDTO(Person person);
}
