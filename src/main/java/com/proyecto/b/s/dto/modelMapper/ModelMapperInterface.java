package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventUpdateRequestDTO;
import com.proyecto.b.s.dto.request.interviewRequestDTO.InterviewRequestDTO;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonRequestDTO;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventResponseDTO;
import com.proyecto.b.s.dto.response.interviewResponseDTO.InterviewResponseDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.*;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDTO personRequestDto);

    Person personUpdateReqDtoToPerson(PersonUpdateRequestDTO personRequestDto);

    PersonResponseDTO personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDTO searchRequestDto);

    SearchResponseDTO searchToSearchResponseDTO(Search search);

    Interview interviewSaveRequestDtoToInterview(InterviewRequestDTO interviewRequestDTO);

    InterviewResponseDTO interviewToInterviewResponseDto(Interview interview);

    Event eventSaveRequestDtoToEvent(EventRequestDTO eventRequestDTO);

    Event eventUpdateRequestDtoToEvent(EventUpdateRequestDTO eventUpdateRequestDTO);

    EventResponseDTO eventToEventResponseDto(Event event);

    EventOption eventOptionRequestDtoToEventOption(EventOptionForEventRequestDTO eventRequestDTO);

    EventOptionForEventResponseDTO eventOptionToEvenOptionResponseDto(EventOption event);

    Client clientReqDTOToClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO clientToClientResponseDTO(Client client);

    Industry industryReqDTOToindustry(IndustryRequestDTO industryRequestDTO);

    IndustryResponseDTO industryToIndustryResponseDTO(Industry industry);

    Rol rolReqDTOToRol(RolRequestDTO rolRequestDTO);

    Seniority seniorityReqDTOToSeniority(SeniorityRequestDTO seniorityRequestDTO);

    RolResponseDTO rolToRolResponseDTO(Rol rol);

    SeniorityResponseDTO seniorityToSeniorityResponseDTO(Seniority seniority);
}