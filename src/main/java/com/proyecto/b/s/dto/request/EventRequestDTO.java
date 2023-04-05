package com.proyecto.b.s.dto.request;

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

    private List<EventOptionRequestDTO> events;

    private Long user;

    private Long person;


    //private List<Search> search;


    //private List<Interview> interviews;
}
