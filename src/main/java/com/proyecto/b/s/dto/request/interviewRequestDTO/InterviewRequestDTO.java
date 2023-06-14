package com.proyecto.b.s.dto.request.interviewRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Setter
@Getter
public class InterviewRequestDTO {
    private PersonForInterviewRequestDTO person;
    private UserForInterviewRequestDTO user;
    private LocalDate dateInterview;
    private LocalTime timeInterview;
    private EventForInterviewRequestDTO event;
    //private String linkMeet;
}
