package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.StatePerson;
import com.proyecto.b.s.service.service.StatePersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/state_person")
@CrossOrigin("*")
public class StatePersonController {

    private final StatePersonService statePersonService;


    public StatePersonController(StatePersonService statePersonService) {
        this.statePersonService = statePersonService;
    }


    //CRUD
    //Lista de estado de persona
    @GetMapping("/list")
    public ResponseEntity<List<StatePerson>> listStatePerson() {
        return ResponseEntity.ok(statePersonService.list());
    }

    //Crear state_person
    @PostMapping("/create")
    public ResponseEntity<StatePerson> create(@RequestBody StatePerson statePerson) {
        StatePerson result = statePersonService.save(statePerson);

        return ResponseEntity.ok(result);
    }

    //Actualizar person
    @PutMapping("/updateActive/{idPerson}")
    public ResponseEntity<PersonResponseDTO> updateActivePerson(@PathVariable Long idPerson, @RequestBody String nameState) throws Exception {
        Person result = statePersonService.updatePersonActiveStatus(idPerson, nameState);

        return ResponseEntity.ok(result);
    }
}
