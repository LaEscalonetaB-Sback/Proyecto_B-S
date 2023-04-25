package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.response.SeniorityResponseDTO;
import com.proyecto.b.s.entity.Seniority;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.SeniorityRepository;
import com.proyecto.b.s.service.service.SeniorityService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeniorityServiceImpl implements SeniorityService {
    private final SeniorityRepository seniorityRepository;
    private final ModelMapper modelMapper;

    public SeniorityServiceImpl(SeniorityRepository seniorityRepository, ModelMapper modelMapper) {
        this.seniorityRepository = seniorityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SeniorityResponseDTO> list() {
        List<Seniority> seniorities = seniorityRepository.findAll();
        HelperValidator.isEmptyList(seniorities);

        return seniorities.stream()
                .map(seniority -> modelMapper.map(seniority, SeniorityResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Seniority findById(Long id) throws Exception {

        return seniorityRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Seniority no encontrado con id: " + id));
    }

    @Override
    public Seniority findByName(String name) {

        return seniorityRepository.findByName(name);
    }
}
