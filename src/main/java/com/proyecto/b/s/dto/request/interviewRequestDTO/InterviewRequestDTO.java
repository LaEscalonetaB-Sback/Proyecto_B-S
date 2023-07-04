package com.proyecto.b.s.dto.request.interviewRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class InterviewRequestDTO {
    private EventForInterviewRequestDTO event;
    private PersonForInterviewRequestDTO person;
    private UserForInterviewRequestDTO userRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
