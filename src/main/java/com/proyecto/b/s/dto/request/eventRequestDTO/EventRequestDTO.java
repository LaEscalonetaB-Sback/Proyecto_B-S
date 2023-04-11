package com.proyecto.b.s.dto.request.eventRequestDTO;

import com.proyecto.b.s.dto.request.SearchRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class EventRequestDTO {

    private Date dateEvent;

    private List<EventOptionForEventRequestDTO> events;

    private UserForEventRequestDTO user;

    private PersonForEventRequestDTO person;


   // private List<SearchRequestDTO> search;


   // private List<InterviewResponseDTO> interviews;
}
