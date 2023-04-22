package com.proyecto.b.s.controller;


import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/person")
@CrossOrigin("*")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Lista de personas
    @GetMapping("/list")
    public ResponseEntity<List<PersonResponseDTO>> searchPerson(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "seniorityGeneral", required = false) List<String> seniorityGeneral,
            @RequestParam(value = "roles", required = false) List<String> roles,
            @RequestParam(value = "skills", required = false) List<String> skills) {

        List<PersonResponseDTO> persons = personService.search(name, lastName, seniorityGeneral, roles, skills);

        return ResponseEntity.ok(persons);
    }


    //Encontrar por id
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) throws Exception {
        Optional<Person> PersonOpt = Optional.ofNullable(personService.obtainPersonId(id));

        return PersonOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear busqueda
    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody @Valid PersonRequestDTO personRequestDto) throws Exception {
        Person result = personService.create(personRequestDto);

        return ResponseEntity.ok(result);
    }

    //Actualizar
    @PutMapping("/update/{Id}")
    public ResponseEntity<PersonResponseDTO> update(@PathVariable Long Id, @RequestBody PersonUpdateRequestDTO personRequestDto) throws Exception {
        PersonResponseDTO result = personService.update(Id, personRequestDto);

        return ResponseEntity.ok(result);
    }


    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PersonResponseDTO> deleteById(@PathVariable Long id) throws Exception {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
