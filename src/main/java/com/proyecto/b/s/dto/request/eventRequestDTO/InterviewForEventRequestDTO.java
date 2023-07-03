package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
public class InterviewForEventRequestDTO {

    private List<String> emailPerson;
    // TODO: 3/7/2023 Admitir uno o mas emails
    private String emailRecruiter;
    private LocalDate dateInterview;
    private LocalDate hour;
    // TODO: 3/7/2023 conexion con google meet
    private String linkMeet;
}