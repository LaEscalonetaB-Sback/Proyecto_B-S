package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.IndustryRequestDTO;
import com.proyecto.b.s.dto.response.IndustryResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Industry;
import com.proyecto.b.s.repository.IndustryRepository;
import com.proyecto.b.s.service.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/industry")
@CrossOrigin("*")
public class IndustryController {
    @Autowired
    private final IndustryService industryService;
    @Autowired
    private final IndustryRepository industryRepository;

    public IndustryController(IndustryService industryService, IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
        this.industryService = industryService;
    }

    //CRUD
    //Lista de industrias
    @GetMapping("/list")
    public ResponseEntity<List<IndustryResponseDTO>> findIndustry() {
        return ResponseEntity.ok(industryService.listIndustry());
    }

    //Encuentra Evento por id
    @GetMapping("/{id}")
    public ResponseEntity<Industry> findOne(@PathVariable Long id) throws Exception {
        Optional<Industry> SearchOpt = Optional.ofNullable(industryService.findById(id));
        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear Industry
    @PostMapping("/create")
    public ResponseEntity<IndustryResponseDTO> create(@RequestBody IndustryRequestDTO industryRequestDto) {
        IndustryResponseDTO result = industryService.saveIndustry(industryRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar Industry
    @PutMapping("/update/{industryId}")
    public ResponseEntity<IndustryResponseDTO> update(@PathVariable Long industryId, @RequestBody IndustryRequestDTO industryRequestDTO) throws Exception {
        if (!industryService.existById(industryId)) {
            return ResponseEntity.notFound().build();
        }
        IndustryResponseDTO result = industryService.updateIndustry(industryId, industryRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar Industry por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id) throws Exception {
        industryService.deleteIndustry(id);
        return ResponseEntity.noContent().build();
    }
}
