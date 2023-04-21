package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.SourceRequestDTO;
import com.proyecto.b.s.dto.response.SourceResponseDTO;
import com.proyecto.b.s.entity.Source;
import com.proyecto.b.s.repository.SourceRepository;
import com.proyecto.b.s.service.service.SourceService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public SourceResponseDTO save(SourceRequestDTO sourceRequestDTO) {
        Source source = modelMapper.map(sourceRequestDTO, Source.class);
        sourceRepository.save(source);
        return modelMapper.map(source, SourceResponseDTO.class);
    }

    @Override
    public Source findById(Long id) throws Exception {
        return sourceRepository.getReferenceById(id);
    }

    @Override
    public SourceResponseDTO update(Long id, SourceRequestDTO sourceRequestDTO) throws EntityNotFoundException {
        Source source = sourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fuente no encontrada con id: " + id));
        String sourceNameOrigin = sourceRequestDTO.getName();

        source.setName(sourceNameOrigin);

        sourceRepository.save(source);

        return modelMapper.map(source, SourceResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        Source entity = sourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fuente no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Fuente no encontrada con id: " + id);
        } else {
            sourceRepository.delete(entity);
        }
    }

    @Override
    public List<SourceResponseDTO> list() {
        List<Source> sources = sourceRepository.findAll();
        HelperValidator.isEmptyList(sources);
        List<SourceResponseDTO> sourceResponseDTOS = new ArrayList<>();

        for (Source aux : sources) {
            SourceResponseDTO source = modelMapper.map(aux, SourceResponseDTO.class);
            sourceResponseDTOS.add(source);
        }

        return sourceResponseDTOS;
    }

    @Override
    public boolean existById(Long id) {
        return sourceRepository.existsById(id);
    }
}
