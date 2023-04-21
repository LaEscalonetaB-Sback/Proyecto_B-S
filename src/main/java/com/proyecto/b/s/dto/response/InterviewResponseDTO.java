package com.proyecto.b.s.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class InterviewResponseDTO {
    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
}
