package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
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
        this.eventOptionService = EventOptionController.this.eventOptionService;
    }

    //CRUD
    //Lista de Evento
    @GetMapping("/list")
    public ResponseEntity<List<SearchResponseDTO>> findSearch() {
        return null;
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
    @PutMapping("/update/{searchId}")
    public ResponseEntity<EventOptionForEventResponseDTO> update(@PathVariable Long eventId, @RequestBody EventOptionForEventRequestDTO eventRequestDTO) throws Exception {
        if (!eventOptionService.existById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        EventOptionForEventResponseDTO result = eventOptionService.updateEventOption(eventId, eventRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar Evento por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id) {
        eventOptionService.deleteEventOption(id);
        return ResponseEntity.noContent().build();
    }
}
