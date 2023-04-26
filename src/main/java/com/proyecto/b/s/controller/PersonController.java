package com.proyecto.b.s.controller;


import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/person")
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
    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    //Encontrar por id
    //@GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) throws Exception {
        Optional<Person> PersonOpt = Optional.ofNullable(personService.obtainPersonId(id));
        if (PersonOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(PersonOpt.get());
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long personId){
        return personService.getPersonById(personId)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    //Crear busqueda
    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody Person person) throws Exception {
       if (person.getId() != null){
            return ResponseEntity.badRequest().build();
        }
       Person result = personService.create(person); return ResponseEntity.ok(result);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person){
        return personService.create(person);
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
    @PutMapping("{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long personId,
                                               @RequestBody Person person){
        return personService.getPersonById(personId)
                .map(savedPerson->{
                    savedPerson.setNameComplete(person.getNameComplete());
                    savedPerson.setLinkedin(person.getLinkedin());
                    savedPerson.setRecruiter(person.getRecruiter());
                    savedPerson.setSeniorityGeneral(person.getSeniorityGeneral());
                    savedPerson.setDni(person.getDni());
                    savedPerson.setEmail(person.getEmail());
                    savedPerson.setCuil(person.getCuil());
                    savedPerson.setPhoneNumber(person.getPhoneNumber());
                    savedPerson.setRemuneration(person.getRemuneration());

                    Person updatedPerson = personService.updatePerson(savedPerson);
                    return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
                })
                .orElseGet(()-> ResponseEntity.notFound().build());

    }

    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deleteById(@PathVariable Long id) throws Exception {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long personId){
        personService.deletePerson(personId);
        return new ResponseEntity<String>("Person deleted successfully!", HttpStatus.OK);
    }
}
