package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.SkillRequestDTO;
import com.proyecto.b.s.dto.response.SkillResponseDTO;
import com.proyecto.b.s.entity.Skill;
import com.proyecto.b.s.service.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/skill")
@CrossOrigin("*")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    //CRUD
    //Lista de skills
    @GetMapping("/list")
    public ResponseEntity<List<SkillResponseDTO>> findInterview() {
        List<SkillResponseDTO> result = skillService.list();

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    //Encuentra skill por id
    @GetMapping("/{id}")
    public ResponseEntity<Skill> findOne(@PathVariable Long id) throws Exception {
        if (!skillService.existById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            Skill skill = skillService.findById(id);
            return ResponseEntity.ok(skill);
        }
    }

    //Crear skill
    @PostMapping("/create")
    public ResponseEntity<SkillResponseDTO> create(@RequestBody SkillRequestDTO skillRequestDTO) {
        SkillResponseDTO result = skillService.save(skillRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Actualizar skill
    @PutMapping("/update/{id}")
    public ResponseEntity<SkillResponseDTO> update(@PathVariable Long id, @RequestBody SkillRequestDTO skillRequestDTO) throws Exception {
        if (!skillService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        SkillResponseDTO result = skillService.update(id, skillRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar skill por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Skill> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
