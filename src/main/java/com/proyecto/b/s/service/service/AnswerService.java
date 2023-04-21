package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer findById(Long id) throws Exception;

    boolean existById(Long id);

    List<Answer> listAnswer();

}
