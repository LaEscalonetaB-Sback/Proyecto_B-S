package com.proyecto.b.s.controller;
//busqueda -> search

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.ClientRepository;
import com.proyecto.b.s.repository.RolRepository;
import com.proyecto.b.s.repository.SeniorityRepository;
import com.proyecto.b.s.repository.StateRepository;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/busqueda")
public class SearchController {
    private final SearchService searchService;
    private final ClientRepository clientRepository;
    private final RolRepository rolRepository;
    private final StateRepository stateRepository;
    private final SeniorityRepository seniorityRepository;

    public SearchController(SearchService searchService, ClientRepository clientRepository, RolRepository rolRepository, StateRepository stateRepository, SeniorityRepository seniorityRepository) {
        this.searchService = searchService;
        this.clientRepository = clientRepository;
        this.rolRepository = rolRepository;
        this.stateRepository = stateRepository;
        this.seniorityRepository = seniorityRepository;
    }


    //CRUD
    //Lista de busquedas
    @GetMapping("/lista")
    public ResponseEntity<List<Search>> findSearch(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) String skills) {
        Client clientObj = null;
        Rol rolObj = null;
        StateSearch stateObj = null;
        Seniority seniorityObj = null;

        if (client != null) {
            clientObj = clientRepository.findByName(client);
        }
        if (rol != null) {
            rolObj = rolRepository.findByName(rol);
        }
        if (state != null) {
            stateObj = stateRepository.findByName(state);
        }
        if (seniority != null) {
            seniorityObj = seniorityRepository.findByName(seniority);
        }

        List<Search> search = searchService.listSearch(clientObj, rolObj, stateObj, seniorityObj, skills);

        if (search.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(search);
        }
    }

    //Encuentra busqueda por id
    @GetMapping("/{id}")
    public ResponseEntity<Search> findOne(@PathVariable Long id){
        Optional<Search> searchOpt = searchService.findById(id);
        return searchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear busqueda
    @PostMapping("/crear")
    public ResponseEntity<Search> create(@RequestBody Search search){
        if (search.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Search result = searchService.saveSearch(search);
        return ResponseEntity.ok(result);
    }

    //Actualizar busqueda
    @PutMapping("/actualizar")
    public ResponseEntity<Search> update(@RequestBody Search search){
        if (search.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!searchService.existById(search.getId())){
            return ResponseEntity.notFound().build();
        }
        Search result = searchService.saveSearch(search);
        return ResponseEntity.ok(result);
    }

    //Eliminar busqueda por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Search> delete(@PathVariable Long id){
        searchService.deleteSearch(id);
        return ResponseEntity.noContent().build();
    }
}
