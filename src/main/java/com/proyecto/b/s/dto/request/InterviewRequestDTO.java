package com.proyecto.b.s.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewRequestDTO {

    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
