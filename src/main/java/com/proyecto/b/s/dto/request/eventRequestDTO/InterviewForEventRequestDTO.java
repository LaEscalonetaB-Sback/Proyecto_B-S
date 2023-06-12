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
    private String emailRecruiter;
    private LocalDate dateInterview;
    private LocalDate hour;
    private String linkMeet;
}