package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Entrevista;

import java.util.List;

public interface EntrevistaService {
    List<Entrevista> listarEntrevistas();
    Entrevista guardarEntrevista(Entrevista entrevista);
    Entrevista actualizarEntrevista(Entrevista entrevista);
    void eliminarEntrevista(Long id);
}
