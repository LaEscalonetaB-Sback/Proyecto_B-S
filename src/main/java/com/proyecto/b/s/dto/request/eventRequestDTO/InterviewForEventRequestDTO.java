package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewForEventRequestDTO {
    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
