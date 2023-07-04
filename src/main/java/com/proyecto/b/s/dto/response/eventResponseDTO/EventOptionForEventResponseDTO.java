package com.proyecto.b.s.dto.response.eventResponseDTO;

import com.proyecto.b.s.dto.request.AnswerRequestDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventOptionForEventResponseDTO {
    private Long id;
    private String name;
    private AnswerRequestDTO feedback;
}
