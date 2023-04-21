package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.response.RolResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Rol;
import com.proyecto.b.s.repository.RolRepository;
import com.proyecto.b.s.service.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/rol")
@CrossOrigin("*")
public class RolController {
    @Autowired
    private final RolService rolService;
    @Autowired
    private final RolRepository rolRepository;

    public RolController(RolService rolService, RolRepository rolRepository) {
        this.rolRepository = rolRepository;
        this.rolService = rolService;
    }

    //CRUD
    //Lista de industrias
    @GetMapping("/list")
    public ResponseEntity<List<RolResponseDTO>> findRol() {
        return ResponseEntity.ok(rolService.listRol());
    }

    //Encuentra Evento por id
    @GetMapping("/{id}")
    public ResponseEntity<Rol> findOne(@PathVariable Long id) throws Exception {
        Optional<Rol> SearchOpt = Optional.ofNullable(rolService.findById(id));
        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear Rol
    @PostMapping("/create")
    public ResponseEntity<RolResponseDTO> create(@RequestBody RolRequestDTO rolRequestDto) {
        RolResponseDTO result = rolService.saveRol(rolRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar Rol
    @PutMapping("/update/{rolId}")
    public ResponseEntity<RolResponseDTO> update(@PathVariable Long rolId, @RequestBody RolRequestDTO rolRequestDTO) throws Exception {
        if (!rolService.existById(rolId)) {
            return ResponseEntity.notFound().build();
        }
        RolResponseDTO result = rolService.updateRol(rolId, rolRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar Rol por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id) throws Exception {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
