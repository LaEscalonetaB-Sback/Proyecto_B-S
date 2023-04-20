package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.IndustryRequestDTO;
import com.proyecto.b.s.dto.response.IndustryResponseDTO;
import com.proyecto.b.s.entity.Industry;
import com.proyecto.b.s.repository.IndustryRepository;
import com.proyecto.b.s.service.service.IndustryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

    public IndustryServiceImpl(IndustryRepository industryRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.industryRepository = industryRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Industry findById(Long id) throws Exception {
        return industryRepository.findById(id).orElseThrow(() -> new Exception("Industria no encontrada"));
    }

    @Override
    public boolean existById(Long id) {
        return industryRepository.existsById(id);
    }

    @Override
    public List<IndustryResponseDTO> listIndustry() {
        List<Industry> industryList = industryRepository.findAll();
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
        Industry updateIndustry = industryRepository.findById(id).orElseThrow(() -> new Exception("La industria no existe"));
        modelMapper.map(industryRequestDTO, updateIndustry);
        industryRepository.save(updateIndustry);

        return modelMapper.map(updateIndustry, IndustryResponseDTO.class);
    }

    @Override
    public void deleteIndustry(Long id) {
        Industry entity = industryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Industria no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Industria no encontrada con id: " + id);
        }
        entity.setActive(false);
        industryRepository.save(entity);
    }
}
