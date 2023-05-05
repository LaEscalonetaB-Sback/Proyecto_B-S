package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.SourceRequestDTO;
import com.proyecto.b.s.dto.response.SourceResponseDTO;
import com.proyecto.b.s.entity.Skill;
import com.proyecto.b.s.entity.Source;
import com.proyecto.b.s.service.service.SourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/source")
@CrossOrigin("*")
public class SourceController {
    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    //CRUD
    //Lista de source
    @GetMapping("/list")
    public ResponseEntity<List<SourceResponseDTO>> find() {
        List<SourceResponseDTO> result = sourceService.list();

        return ResponseEntity.ok(result);
    }

    //Encuentra source por id
    @GetMapping("/{id}")
    public ResponseEntity<Source> findOne(@PathVariable Long id) throws Exception {
        Source source = sourceService.findById(id);

        return ResponseEntity.ok(source);
    }

    //Crear source
    @PostMapping("/create")
    public ResponseEntity<SourceResponseDTO> create(@RequestBody SourceRequestDTO sourceRequestDTO) {
        SourceResponseDTO result = sourceService.save(sourceRequestDTO);

        return ResponseEntity.ok(result);
    }

    //Actualizar source
    @PutMapping("/update/{id}")
    public ResponseEntity<SourceResponseDTO> update(@PathVariable Long id, @RequestBody SourceRequestDTO sourceRequestDTO) throws Exception {
        SourceResponseDTO result = sourceService.update(id, sourceRequestDTO);

        return ResponseEntity.ok(result);
    }

    //Eliminar source por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Skill> delete(@PathVariable Long id) throws Exception {
        sourceService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
