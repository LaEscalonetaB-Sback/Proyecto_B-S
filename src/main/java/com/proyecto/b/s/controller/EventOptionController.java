package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.EventOption;
import com.proyecto.b.s.repository.EventOptionRepository;
import com.proyecto.b.s.service.service.EventOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/event-option")
@CrossOrigin("*")
public class EventOptionController {
    private final EventOptionService eventOptionService;
    @Autowired
    private EventOptionRepository eventOptionRepository;

    public EventOptionController(EventOptionService eventOptionService, EventOptionRepository eventOptionRepository) {
        this.eventOptionService = eventOptionService;
        this.eventOptionRepository = eventOptionRepository;
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

    // TODO: 11/4/2023 eliminar encontrar opciones de evento
    //Encuentra Evento por id
    @GetMapping("/{id}")
    public ResponseEntity<EventOption> findOne(@PathVariable Long id) throws Exception {
        Optional<EventOption> SearchOpt = Optional.ofNullable(eventOptionService.findById(id));
        return SearchOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: 11/4/2023 eliminar crear eventoOption ya que no debemos crear la opcion del evento
    //Crear Evento
    @PostMapping("/create")
    public ResponseEntity<EventOptionForEventResponseDTO> create(@RequestBody EventOptionForEventRequestDTO eventRequestDto) {
        EventOptionForEventResponseDTO result = eventOptionService.saveEventOption(eventRequestDto);
        return ResponseEntity.ok(result);
    }

    // TODO: 11/4/2023 eliminar update del eventOption
    //Actualizar Evento
    @PutMapping("/update/{searchId}")
    public ResponseEntity<EventOptionForEventResponseDTO> update(@PathVariable Long eventId, @RequestBody EventOptionForEventRequestDTO eventRequestDTO) throws Exception {
        if (!eventOptionService.existById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        EventOptionForEventResponseDTO result = eventOptionService.updateEventOption(eventId, eventRequestDTO);
        return ResponseEntity.ok(result);
    }

    // TODO: 11/4/2023 eliminar eliminar evento por id
    //Eliminar Evento por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> delete(@PathVariable Long id) {
        eventOptionService.deleteEventOption(id);
        return ResponseEntity.noContent().build();
    }
}
