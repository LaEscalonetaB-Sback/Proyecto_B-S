package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.service.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/interview")
@CrossOrigin("*")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    //CRUD
    //Lista de entrevistas
    @GetMapping("/list")
    public ResponseEntity<List<InterviewResponseDTO>> findInterview() {
        List<InterviewResponseDTO> interview = interviewService.listInterview();

        return ResponseEntity.ok(interview);
    }

    //Encuentra entrevista por id
    @GetMapping("/{id}")
    public ResponseEntity<Interview> findOne(@PathVariable Long id) throws Exception {
        Optional<Interview> SearchOpt = Optional.ofNullable(interviewService.findById(id));

        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear entrevista
    @PostMapping("/create")
    public ResponseEntity<InterviewResponseDTO> create(@RequestBody InterviewRequestDTO interviewRequestDto) {
        InterviewResponseDTO result = interviewService.saveInterview(interviewRequestDto);

        return ResponseEntity.ok(result);
    }

    //Actualizar entrevista
    @PutMapping("/update/{id}")
    public ResponseEntity<InterviewResponseDTO> update(@PathVariable Long id, @RequestBody InterviewRequestDTO interviewRequestDto) throws Exception {
        InterviewResponseDTO result = interviewService.updateInterview(id, interviewRequestDto);

        return ResponseEntity.ok(result);
    }

    //Eliminar entrevista por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Interview> delete(@PathVariable Long id) throws Exception {
        interviewService.deleteInterview(id);

        return ResponseEntity.noContent().build();
    }
}
