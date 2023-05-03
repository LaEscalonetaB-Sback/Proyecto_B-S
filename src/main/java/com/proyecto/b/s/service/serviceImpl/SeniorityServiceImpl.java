package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.SeniorityRequestDTO;
import com.proyecto.b.s.dto.response.SeniorityResponseDTO;
import com.proyecto.b.s.entity.Seniority;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.SeniorityRepository;
import com.proyecto.b.s.service.service.SeniorityService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeniorityServiceImpl implements SeniorityService {
    private final SeniorityRepository seniorityRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public SeniorityServiceImpl(SeniorityRepository seniorityRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.seniorityRepository = seniorityRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Seniority findById(Long id) throws Exception {

        return seniorityRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Seniority no encontrado con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return seniorityRepository.existsById(id);
    }

    @Override
    public List<SeniorityResponseDTO> listSeniority() {
        List<Seniority> seniorityList = seniorityRepository.findAll();
        HelperValidator.isEmptyList(seniorityList);

        return seniorityList.stream()
                .map(seniority -> modelMapper.map(seniority, SeniorityResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SeniorityResponseDTO saveSeniority(SeniorityRequestDTO seniorityRequestDTO) {
        Seniority newSeniority = modelMapperInterface.seniorityReqDTOToSeniority(seniorityRequestDTO);
        seniorityRepository.save(newSeniority);

        return modelMapperInterface.seniorityToSeniorityResponseDTO(newSeniority);
    }

    @Override
    public SeniorityResponseDTO updateSeniority(Long id, SeniorityRequestDTO seniorityRequestDTO) throws Exception {
        Seniority updateSeniority = findById(id);
        modelMapper.map(seniorityRequestDTO, updateSeniority);
        seniorityRepository.save(updateSeniority);

        return modelMapper.map(updateSeniority, SeniorityResponseDTO.class);
    }

    @Override
    public Seniority findByName(String name) {
        return Optional.ofNullable(seniorityRepository.findByName(name))
                .orElseThrow(()-> new InvalidResourceException("Seniority no encontrado con el nombre " + name + "."));
    }

    @Override
    public void deleteSeniority(Long id) throws Exception {
        Seniority entity = findById(id);
        entity.setActive(false);
        seniorityRepository.save(entity);
    }
}
