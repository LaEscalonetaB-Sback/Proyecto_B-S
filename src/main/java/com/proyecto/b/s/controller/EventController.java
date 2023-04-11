package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.response.EventResponseDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.service.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/event")
@CrossOrigin("*")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = EventController.this.eventService;
    }

    //CRUD
    //Lista de Evento
    @GetMapping("/list")
    public ResponseEntity<List<SearchResponseDTO>> findSearch() {
        return null;
    }


    //Encuentra Evento por id
    @GetMapping("/{id}")
    public ResponseEntity<Event> findOne(@PathVariable Long id) throws Exception {
        Optional<Event> SearchOpt = Optional.ofNullable(eventService.findById(id));
        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear Evento
    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> create(@RequestBody EventRequestDTO eventRequestDto) {
        EventResponseDTO result = eventService.saveEvent(eventRequestDto);
        return ResponseEntity.ok(result);
    }

    //Actualizar Evento
    @PutMapping("/update/{searchId}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long eventId, @RequestBody EventRequestDTO eventRequestDTO)  throws Exception  {
        if (!eventService.existById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        EventResponseDTO result = eventService.updateEvent(eventId, eventRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar Evento por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
