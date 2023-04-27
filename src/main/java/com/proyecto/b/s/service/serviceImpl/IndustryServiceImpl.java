package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.IndustryRequestDTO;
import com.proyecto.b.s.dto.response.IndustryResponseDTO;
import com.proyecto.b.s.entity.Industry;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.IndustryRepository;
import com.proyecto.b.s.service.service.IndustryService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public IndustryServiceImpl(IndustryRepository industryRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.industryRepository = industryRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Industry findById(Long id) throws Exception {

        return industryRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Industria no encontrada con id: " + id));
    }

    @Override
    public boolean existById(Long id) {

        return industryRepository.existsById(id);
    }

    @Override
    public List<IndustryResponseDTO> listIndustry() {
        List<Industry> industryList = industryRepository.findAll();
        HelperValidator.isEmptyList(industryList);

        return industryList.stream()
                .map(industry -> modelMapper.map(industry, IndustryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public IndustryResponseDTO saveIndustry(IndustryRequestDTO industryRequestDTO) {
        Industry newIndustry = modelMapperInterface.industryReqDTOToindustry(industryRequestDTO);
        industryRepository.save(newIndustry);

        return modelMapperInterface.industryToIndustryResponseDTO(newIndustry);
    }

    @Override
    public IndustryResponseDTO updateIndustry(Long id, IndustryRequestDTO industryRequestDTO) throws Exception {
        Industry updateIndustry = findById(id);
        modelMapper.map(industryRequestDTO, updateIndustry);
        industryRepository.save(updateIndustry);

        return modelMapper.map(updateIndustry, IndustryResponseDTO.class);
    }

    @Override
    public void deleteIndustry(Long id) throws Exception {
        Industry entity = findById(id);
        entity.setActive(false);
        industryRepository.save(entity);
    }

    @Override
    public Industry findByName(String name) {

        return Optional.ofNullable(industryRepository.findByName(name))
                .orElseThrow(() -> new InvalidResourceException("Industria no encontrada con el nombre " + name + "."));
    }
}
