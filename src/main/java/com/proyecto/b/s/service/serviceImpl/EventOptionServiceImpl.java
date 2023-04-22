package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.EventOption;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.EventOptionRepository;
import com.proyecto.b.s.service.service.EventOptionService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventOptionServiceImpl implements EventOptionService {
    private final EventOptionRepository eventOptionRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public EventOptionServiceImpl(EventOptionRepository eventOptionRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.eventOptionRepository = eventOptionRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Answer> getAnswersByEventOptionName(String eventOptionName) {

        return eventOptionRepository.findAnswersByEventOptionName(eventOptionName);
    }

    @Override
    public List<String> getEventOptionNames() {
        List<String> optionNames = new ArrayList<>();
        List<EventOption> eventOptions = eventOptionRepository.findAll();
        HelperValidator.isEmptyList(eventOptions);
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
        EventOption updateEventOption = findById(eventId);
        modelMapper.map(eventRequestDTO, updateEventOption);
        eventOptionRepository.save(updateEventOption);

        return modelMapper.map(updateEventOption, EventOptionForEventResponseDTO.class);
    }

    @Override
    public void deleteEventOption(Long id) throws Exception {
        EventOption entity = findById(id);
        entity.setActive(false);
        eventOptionRepository.save(entity);
    }

    @Override
    public EventOption findById(Long id) throws Exception {

        return eventOptionRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Evento no encontrado con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return eventOptionRepository.existsById(id);
    }
}