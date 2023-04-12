package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.EventOption;
import com.proyecto.b.s.repository.EventOptionRepository;
import com.proyecto.b.s.service.service.EventOptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventOptionServiceImpl implements EventOptionService {
    @Autowired
    private EventOptionRepository eventOptionRepository;

    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

    public EventOptionServiceImpl(EventOptionRepository eventOptionRepository, ModelMapperInterface modelMapperInterface) {
        this.eventOptionRepository = eventOptionRepository;
        this.modelMapperInterface = modelMapperInterface;
    }

    /*@Override
    public List<EventOptionForEventResponseDTO> listEventoption() {
        List<EventOption> eventOptionList = eventOptionRepository.findAll();
        return eventOptionList.stream()
                .map(eventOption -> modelMapper.map(eventOption, EventOptionForEventResponseDTO.class))
                .collect(Collectors.toList());
    }*/

    @Override
    public List<Answer> getAnswersByEventOptionName(String eventOptionName) {
        return eventOptionRepository.findAnswersByEventOptionName(eventOptionName);
    }

    @Override
    public List<String> getEventOptionNames() {
        List<String> optionNames = new ArrayList<>();
        List<EventOption> eventOptions = eventOptionRepository.findAll();
        for (EventOption eventOption : eventOptions) {
            optionNames.add(eventOption.getName());
        }
        return optionNames;
    }

    @Override
    public EventOptionForEventResponseDTO saveEventOption(EventOptionForEventRequestDTO eventRequestDTO) {
        EventOption newEventOption = modelMapperInterface.eventOptionRequestDtoToEventOption(eventRequestDTO);
        eventOptionRepository.save(newEventOption);
        return modelMapperInterface.eventOptionToEvenOptionResponseDto(newEventOption);
    }

    @Override
    public EventOptionForEventResponseDTO updateEventOption(Long eventId, EventOptionForEventRequestDTO eventRequestDTO) throws Exception {
        EventOption updateEventOption = eventOptionRepository.findById(eventId).orElseThrow(() -> new Exception("La entrevista no existe"));
        modelMapper.map(eventRequestDTO, updateEventOption);
        eventOptionRepository.save(updateEventOption);

        return modelMapper.map(updateEventOption, EventOptionForEventResponseDTO.class);

    }

    @Override
    public void deleteEventOption(Long id) {
        EventOption entity = eventOptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Opcion de Evento no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Entrevista no encontrada con id: " + id);
        }
        entity.setActive(false);
        eventOptionRepository.save(entity);

    }

    @Override
    public EventOption findById(Long id) throws Exception {
        return eventOptionRepository.findById(id).orElseThrow(() -> new Exception("La busqueda no existe"));
    }

    @Override
    public boolean existById(Long id) {
        return eventOptionRepository.existsById(id);
    }
}