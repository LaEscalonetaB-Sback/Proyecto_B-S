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
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements ModelMapperInterface {
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //Person
    @Override
    public Person personReqDtoToPerson(PersonRequestDTO personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }

    @Override
    public Person personUpdateReqDtoToPerson(PersonUpdateRequestDTO personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }

    @Override
    public PersonResponseDTO personToPersonResponseDTO(Person person) {
        return modelMapper.map(person, PersonResponseDTO.class);
    }

    //Search
    @Override
    public Search searchReqDtoToSearch(SearchRequestDTO searchRequestDto) {
        return modelMapper.map(searchRequestDto, Search.class);
    }

    @Override
    public SearchResponseDTO searchToSearchResponseDTO(Search search) {
        return modelMapper.map(search, SearchResponseDTO.class);
    }

    @Override
    public Interview interviewSaveRequestDtoToInterview(InterviewRequestDTO interviewRequestDTO) {
        return modelMapper.map(interviewRequestDTO, Interview.class);
    }

    @Override
    public InterviewResponseDTO interviewToInterviewResponseDto(Interview interview) {
        return modelMapper.map(interview, InterviewResponseDTO.class);
    }
}
