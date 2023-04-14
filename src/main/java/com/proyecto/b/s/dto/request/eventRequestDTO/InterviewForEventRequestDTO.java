package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class InterviewForEventRequestDTO {

    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
