package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewForEventResponseDTO {
    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
