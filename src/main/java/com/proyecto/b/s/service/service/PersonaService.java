package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> listarPersonas();
    Persona guardarPersona(Persona persona);
    Persona actualizarPersona(Persona persona);
    void eliminarPersona(Long id);
}
