package com.proyecto.b.s.controller;


import com.proyecto.b.s.entity.Persona;
import com.proyecto.b.s.repository.PersonaRepository;
import com.proyecto.b.s.service.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/personas")
public class PersonaController {

    private PersonaService personaService;

    private PersonaRepository personaRepository;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }



    @GetMapping("/")
    public List<Persona> encontrarTodos(){
        return personaService.listarPersonas();
    }

    //Encontrar por id
    @GetMapping("/{id}")
    public ResponseEntity<Persona> encontrarPorID(@PathVariable Long id) throws Exception {
        Optional<Persona> PersonaOpt = Optional.ofNullable(personaService.obtenerPersonaId(id));
        if (PersonaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(PersonaOpt.get());
        }
    }

    //Crear busqueda
    @PostMapping("/crear")
    public ResponseEntity<Persona> crear(@RequestBody Persona persona) throws Exception {
        if (persona.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Persona resultado = personaService.guardarPersona(persona);
        return ResponseEntity.ok(resultado);
    }

    //Actualizar
    @PutMapping("/actualizar")
    public ResponseEntity<Persona> actualizar(@RequestBody Persona persona) throws Exception {
        if (persona.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!personaRepository.existsById(persona.getId())){
            return ResponseEntity.notFound().build();
        }
        Persona resultado = personaService.guardarPersona(persona);
        return ResponseEntity.ok(resultado);
    }


    //Eliminar por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Persona> eliminarPorId(@PathVariable Long id) throws Exception {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
