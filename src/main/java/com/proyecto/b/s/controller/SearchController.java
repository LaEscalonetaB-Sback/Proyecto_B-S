package com.proyecto.b.s.controller;
//busqueda -> search

import com.proyecto.b.s.dto.request.SearchRequestDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/search")
@CrossOrigin("*")
public class SearchController {
    private final SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //CRUD
    //Lista de busquedas
    @GetMapping("/list")
    public ResponseEntity<List<SearchResponseDTO>> findSearch(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) List<String> seniority,
            @RequestParam(required = false) List<String> skills) {
        List<SearchResponseDTO> search = searchService.listSearch(client, rol, state, seniority, skills);

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
    @PostMapping("/create")
    public ResponseEntity<SearchResponseDTO> create(@RequestBody SearchRequestDTO searchRequestDto){
        SearchResponseDTO result = searchService.saveSearch(searchRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar busqueda
    @PutMapping("/update/{searchId}")
    public ResponseEntity<SearchResponseDTO> update(@PathVariable Long searchId, @RequestBody SearchRequestDTO searchRequestDto) throws EntityNotFoundException {
        if (!searchService.existById(searchId)) {
            return ResponseEntity.notFound().build();
        }
        SearchResponseDTO result = searchService.update(searchId, searchRequestDto);
        return ResponseEntity.ok(result);
    }

    //Eliminar busqueda por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Search> delete(@PathVariable Long id){
        searchService.deleteSearch(id);
        return ResponseEntity.noContent().build();
    }
}
