package com.proyecto.b.s.controller;


import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDto;
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
    public ResponseEntity<List<PersonResponseDto>> searchPerson(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "seniorityGeneral", required = false) String seniorityGeneral,
            @RequestParam(value = "roles", required = false) List<String> roles,
            @RequestParam(value = "skills", required = false) List<String> skills) {

        List<PersonResponseDto> persons = personService.search(name, lastName, seniorityGeneral, roles, skills);
        return ResponseEntity.ok(persons);
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
    public ResponseEntity<Person> create(@RequestBody PersonRequestDto personRequestDto) throws Exception {
        Person result = personService.create(personRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar
    @PutMapping("/update/{Id}")
    public ResponseEntity<PersonResponseDto> update(@PathVariable Long Id, @RequestBody PersonUpdateRequestDTO personRequestDto) throws Exception {
       if(!personService.existById(Id)){
           return ResponseEntity.notFound().build();
       }
       PersonResponseDto result = personService.update(Id, personRequestDto);
       return ResponseEntity.ok(result);
    }



    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PersonResponseDto> deleteById(@PathVariable Long id) throws Exception {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
