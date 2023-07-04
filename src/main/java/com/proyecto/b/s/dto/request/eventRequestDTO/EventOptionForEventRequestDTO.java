package com.proyecto.b.s.dto.request.eventRequestDTO;

import com.proyecto.b.s.dto.request.AnswerRequestDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventOptionForEventRequestDTO {
    private String name;
    private AnswerRequestDTO feedback;
}
