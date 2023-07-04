package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.AnswerRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer findById(Long id) throws Exception;

    boolean existById(Long id);

    List<Answer> listAnswer();

    Answer findByName(String name);
    public AnswerRequestDTO save(AnswerRequestDTO event);

}
