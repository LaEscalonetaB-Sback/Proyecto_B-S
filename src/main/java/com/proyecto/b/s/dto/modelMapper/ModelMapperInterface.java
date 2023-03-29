package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.dto.request.PersonRequestDto;

import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.entity.Person;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDto personRequestDto);


    Person personUpdateReqDtoToPerson(PersonUpdateRequestDTO personRequestDto);

    PersonResponseDto personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDto searchRequestDto);
    SearchResponseDto searchToSearchResponseDTO(Search search);


}
