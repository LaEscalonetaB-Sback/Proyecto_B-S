package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventUpdateRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.repository.EventRepository;
import com.proyecto.b.s.service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/event")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private final EventService eventService;
    @Autowired
    private final EventRepository eventRepository;

    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    //CRUD
    //Lista de Evento
    @GetMapping("/list")
    public ResponseEntity<List<EventResponseDTO>> findEvent(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(required = false) Long person,
            @RequestParam(required = false) Long user,
            @RequestParam(required = false) Long search) {
        List<EventResponseDTO> event = eventService.listEvent(date, person, user, search);

        if (event.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(event);
        }
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
    @PutMapping("/update/{eventId}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long eventId, @RequestBody EventUpdateRequestDTO eventUpdateRequestDTO) throws Exception {
        if (!eventService.existById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        EventResponseDTO result = eventService.updateEvent(eventId, eventUpdateRequestDTO);
        return ResponseEntity.ok(result);
    }

    //Eliminar Evento por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id)throws Exception {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
