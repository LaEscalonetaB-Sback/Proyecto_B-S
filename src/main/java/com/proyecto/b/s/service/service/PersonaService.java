package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> listarPersonas();
    Persona guardarPersona(Persona persona) throws Exception;

    Persona obtenerPersonaId(Long id) throws Exception;

    Persona actualizarPersona(Persona persona) throws Exception;


    void eliminarPersona(Long id) throws Exception;
}
