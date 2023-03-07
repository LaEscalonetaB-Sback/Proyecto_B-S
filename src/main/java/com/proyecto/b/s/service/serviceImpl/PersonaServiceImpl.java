package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Persona;
import com.proyecto.b.s.repository.PersonaRepository;
import com.proyecto.b.s.service.service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }
    @Override
    public List<Persona> listarPersonas() {
        List <Persona> listaAux = personaRepository.findAll();

        final List<Persona> personasActivas =
                listaAux.stream()
                        .filter(persona -> persona.getActivo())
                        .collect(Collectors.toList());
        return personasActivas;
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
        to.setFuentes(from.getFuentes());
        to.setFechaContratacion(from.getFechaContratacion());
        to.setEstadosPersonas(from.getEstadosPersonas());
        to.setSkills(from.getSkills());
        to.setRecruiter(from.getRecruiter());
        to.setSeniority(from.getSeniority());
        to.setEmail(from.getEmail());
        to.setCuil(from.getCuil());
        to.setTelefono(from.getTelefono());
        to.setRemuneracion(from.getRemuneracion());
        to.setIndustrias(from.getIndustrias());

    }

    @Override
    public void eliminarPersona(Long id) throws Exception {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(()-> new Exception("Persona no encontrada -" + this.getClass().getName()));

//        personaRepository.delete(persona);
        persona.setActivo(false);
    }


}
