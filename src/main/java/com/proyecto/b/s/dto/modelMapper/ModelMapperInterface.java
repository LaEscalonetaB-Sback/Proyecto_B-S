package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.entity.*;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDto personRequestDto);
    PersonResponseDto personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDto searchRequestDto);
    SearchResponseDto searchToSearchResponseDTO(Search search);


}
