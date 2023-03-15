package com.proyecto.b.s.controller;


import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/person")
@CrossOrigin("*")
public class PersonController {

    private PersonService personService;

    private PersonRepository personRepository;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    //Lista de busquedas
    @GetMapping("/list")
    public List<Person> findAll(@RequestParam(required = false) String nameComplete,
                                @RequestParam(required = false) String rol,
                                @RequestParam(required = false) String seniority,
                                @RequestParam(required = false) String skill){
        return personService.list(nameComplete,rol, seniority,skill);
    }



    //Encontrar por id
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) throws Exception {
        Optional<Person> PersonOpt = Optional.ofNullable(personService.obtainPersonId(id));
        if (!PersonOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(PersonOpt.get());
        }
    }

    //Crear busqueda
    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody Person person) throws Exception {
        if (person.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Person result = personService.create(person);
        return ResponseEntity.ok(result);
    }

    //Actualizar
    @PutMapping("/update")
    public ResponseEntity<Person> update(@RequestBody Person person) throws Exception {
        if (person.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!personService.existById(person.getId())){
            return ResponseEntity.notFound().build();
        }
        Person result = personService.update(person);
        return ResponseEntity.ok(result);
    }


    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deleteById(@PathVariable Long id) throws Exception {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
