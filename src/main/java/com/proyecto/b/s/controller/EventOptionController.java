package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.EventOption;
import com.proyecto.b.s.service.service.EventOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/event-option")
@CrossOrigin("*")
public class EventOptionController {
    private final EventOptionService eventOptionService;

    public EventOptionController(EventOptionService eventOptionService) {
        this.eventOptionService = eventOptionService;
    }

    //CRUD
    //Lista de Answer segun cada EventOptions
    @GetMapping("/list")
    public List<Answer> AnswersByEventOptionName(@RequestParam String eventOptionName) {

        return eventOptionService.getAnswersByEventOptionName(eventOptionName);
    }

    //Lista de EventOptions
    @GetMapping("/options")
    public List<String> getEventOptionNames() {

        return eventOptionService.getEventOptionNames();
    }

    //Encuentra Evento por id
    @GetMapping("/{id}")
    public ResponseEntity<EventOption> findOne(@PathVariable Long id) throws Exception {
        Optional<EventOption> SearchOpt = Optional.ofNullable(eventOptionService.findById(id));

        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear Evento
    @PostMapping("/create")
    public ResponseEntity<EventOptionForEventResponseDTO> create(@RequestBody EventOptionForEventRequestDTO eventRequestDto) {
        EventOptionForEventResponseDTO result = eventOptionService.saveEventOption(eventRequestDto);

        return ResponseEntity.ok(result);
    }

    //Actualizar Evento
    @PutMapping("/update/{eventId}")
    public ResponseEntity<EventOptionForEventResponseDTO> update(@PathVariable Long eventId, @RequestBody EventOptionForEventRequestDTO eventRequestDTO) throws Exception {
        EventOptionForEventResponseDTO result = eventOptionService.updateEventOption(eventId, eventRequestDTO);

        return ResponseEntity.ok(result);
    }

}
