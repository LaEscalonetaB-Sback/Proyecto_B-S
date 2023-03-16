package com.proyecto.b.s.controller;
//busqueda -> search

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/busqueda")
@CrossOrigin("*")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //CRUD
    //Lista de busquedas
    @GetMapping("/lista")
    public ResponseEntity<List<Search>> findSearch(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) List<String> skills) {
        List<Search> search = searchService.getSearches(client, rol, state, seniority, skills);

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
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Search> update(@PathVariable Long id, @RequestBody Search search) {
       /* try {
            Search updatedSearch = searchService.updateSearch(id, search);
            return ResponseEntity.ok(updatedSearch);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }*/
        return ResponseEntity.ok().build();
    }

    //Eliminar busqueda por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Search> delete(@PathVariable Long id){
        searchService.deleteSearch(id);
        return ResponseEntity.noContent().build();
    }
}
