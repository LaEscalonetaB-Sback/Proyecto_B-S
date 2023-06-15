package com.proyecto.b.s.dto.response.interviewResponseDTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InterviewResponseDTO {
    private Long id;
    private PersonForInterviewResponseDTO person;
    private UserForInterviewResponseDTO user;
    private LocalDate dateInterview;
    private LocalTime timeInterview;
}