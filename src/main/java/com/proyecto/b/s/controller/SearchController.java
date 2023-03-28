package com.proyecto.b.s.controller;
//busqueda -> search

import com.proyecto.b.s.dto.request.SearchRequestDto;
import com.proyecto.b.s.dto.response.SearchResponseDto;
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
    public ResponseEntity<List<SearchResponseDto>> findSearch(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) List<String> skills) {
        List<SearchResponseDto> search = searchService.listSearch(client, rol, state, seniority, skills);

        if (search.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(search);
        }
    }

    //Encuentra busqueda por id
    @GetMapping("/{id}")
    public ResponseEntity<Search> findOne(@PathVariable Long id) throws Exception {
        Optional<Search> SearchOpt = Optional.ofNullable(searchService.findById(id));
        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear busqueda
    @PostMapping("/crear")
    public ResponseEntity<Search> create(@RequestBody SearchRequestDto searchRequestDto){
        Search result = searchService.saveSearch(searchRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar busqueda
    @PutMapping("/actualizar")
    public ResponseEntity<Search> update(@RequestBody Search search) throws Exception {
        if (search.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!searchService.existById(search.getId())){
            return ResponseEntity.notFound().build();
        }
        Search result = searchService.update(search);
        return ResponseEntity.ok(result);
    }

    //Eliminar busqueda por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Search> delete(@PathVariable Long id){
        searchService.deleteSearch(id);
        return ResponseEntity.noContent().build();
    }
}
