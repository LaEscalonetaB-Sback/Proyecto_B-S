package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.SeniorityRequestDTO;
import com.proyecto.b.s.dto.response.SeniorityResponseDTO;
import com.proyecto.b.s.entity.Seniority;
import com.proyecto.b.s.service.service.SeniorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/seniority")
@CrossOrigin("*")
public class SeniorityController {
    private final SeniorityService seniorityService;

    public SeniorityController(SeniorityService seniorityService) {
        this.seniorityService = seniorityService;
    }

    //CRUD
    //Lista de seniority
    @GetMapping("/list")
    public ResponseEntity<List<SeniorityResponseDTO>> findSeniority() {
        return ResponseEntity.ok(seniorityService.listSeniority());
    }

    //Encuentra seniority por id
    @GetMapping("/{id}")
    public ResponseEntity<Seniority> findOne(@PathVariable Long id) throws Exception {
        Optional<Seniority> SearchOpt = Optional.ofNullable(seniorityService.findById(id));

        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear Seniority
    @PostMapping("/create")
    public ResponseEntity<SeniorityResponseDTO> create(@RequestBody SeniorityRequestDTO seniorityRequestDTO) {
        SeniorityResponseDTO result = seniorityService.saveSeniority(seniorityRequestDTO);

        return ResponseEntity.ok(result);
    }

    //Actualizar Seniority
    @PutMapping("/update/{seniorityId}")
    public ResponseEntity<SeniorityResponseDTO> update(@PathVariable Long seniorityId, @RequestBody SeniorityRequestDTO seniorityRequestDTO) throws Exception {
        SeniorityResponseDTO result = seniorityService.updateSeniority(seniorityId, seniorityRequestDTO);

        return ResponseEntity.ok(result);
    }

    //Eliminar Seniority por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Seniority> delete(@PathVariable Long id) throws Exception {
        seniorityService.deleteSeniority(id);

        return ResponseEntity.noContent().build();
    }
}