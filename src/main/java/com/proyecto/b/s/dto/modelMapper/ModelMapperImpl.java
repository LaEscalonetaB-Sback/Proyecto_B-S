package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.*;
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

    @Override
    public Event eventSaveRequestDtoToEvent(EventRequestDTO eventRequestDTO) {
        return modelMapper.map(eventRequestDTO, Event.class);
    }

    @Override
    public EventResponseDTO eventToEventResponseDto(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    @Override
    public EventOption eventOptionRequestDtoToEventOption(EventOptionForEventRequestDTO eventRequestDTO) {
        return  modelMapper.map(eventRequestDTO, EventOption.class);
    }

    @Override
    public EventOptionForEventResponseDTO eventOptionToEvenOptionResponseDto(EventOption event) {
        return modelMapper.map(event, EventOptionForEventResponseDTO.class);
    }
}
