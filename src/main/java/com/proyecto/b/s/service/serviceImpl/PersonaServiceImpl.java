package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Persona;
import com.proyecto.b.s.repository.PersonaRepository;
import com.proyecto.b.s.service.service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> listarPersonas() {
       return personaRepository.findAll();
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }


    @Override
    public Persona obtenerPersonaId(Long id) throws Exception {
    Persona persona = personaRepository.findById(id).orElseThrow(() -> new Exception("La persona no existe"));
    return persona;
}

    @Override
    public Persona actualizarPersona(Persona fromPersona) throws Exception {
        Persona toPersona = obtenerPersonaId(fromPersona.getId());
        mapPersona(fromPersona, toPersona);
        return personaRepository.save(toPersona);
    }


    protected  void mapPersona(Persona from, Persona to){
        to.setApellido(from.getApellido());
        to.setNombre(from.getNombre());
        to.setDni(from.getDni());
        to.setLinkedin(from.getLinkedin());
        //to.setFuenteContacto(from.getFuenteContacto());
        to.setFechaContratacion(from.getFechaContratacion());
        //to.setEstado(from.getEstado());
        //to.setSkills(from.getSkills());
        to.setRol(from.getRol());
        to.setRecruiter(from.getRecruiter());
        //to.setSeniority(from.getSeniority());
        to.setEmail(from.getEmail());
        to.setCuil(from.getCuil());
        to.setTelefono(from.getTelefono());
        to.setRemuneracion(from.getRemuneracion());
        to.setIndustria(from.getIndustria());

    }

    @Override
    public void eliminarPersona(Long id) throws Exception {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(()-> new Exception("Persona no encontrada -" + this.getClass().getName()));

        personaRepository.delete(persona);
    }

}
