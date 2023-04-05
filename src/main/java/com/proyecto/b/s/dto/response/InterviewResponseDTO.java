package com.proyecto.b.s.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewResponseDTO {
    private Long id;
    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
