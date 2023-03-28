package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.request.SearchRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.dto.response.SearchResponseDto;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.Search;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDto personRequestDto);

    PersonResponseDto personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDto searchRequestDto);

    SearchResponseDto searchToSearchResponseDTO(Search search);
}
