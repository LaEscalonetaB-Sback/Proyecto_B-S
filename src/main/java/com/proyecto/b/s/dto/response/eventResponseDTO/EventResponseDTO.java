package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class EventResponseDTO {

    private Date dateEvent;

    private List<EventOptionForEventResponseDTO> events;

    private UserForEventResponseTO user;

    private PersonForEventResponseDTO person;

  //  private List<SearchRequestDTO> search;


    //private List<InterviewResponseDTO> interviews;
}
