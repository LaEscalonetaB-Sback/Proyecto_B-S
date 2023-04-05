package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.request.SearchRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.Search;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDTO personRequestDto);


    Person personUpdateReqDtoToPerson(PersonUpdateRequestDTO personRequestDto);

    PersonResponseDTO personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDTO searchRequestDto);
    SearchResponseDTO searchToSearchResponseDTO(Search search);

    Interview interviewSaveRequestDtoToInterview(InterviewRequestDTO interviewRequestDTO);

    InterviewResponseDTO interviewToInterviewResponseDto(Interview interview);

}
