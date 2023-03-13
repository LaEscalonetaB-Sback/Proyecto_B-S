package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Interview;

import java.util.List;

public interface InterviewService {
    List<Interview> listarEntrevistas();
    Interview guardarEntrevista(Interview entrevista);
    Interview actualizarEntrevista(Interview entrevista);
    void eliminarEntrevista(Long id);
}
