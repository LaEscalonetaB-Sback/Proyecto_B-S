package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.SourceRequestDTO;
import com.proyecto.b.s.dto.response.SourceResponseDTO;
import com.proyecto.b.s.entity.Source;
import com.proyecto.b.s.repository.SourceRepository;
import com.proyecto.b.s.service.service.SourceService;
import com.proyecto.b.s.utils.HelperValidator;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;
    private final ModelMapper modelMapper;

    public SourceServiceImpl(SourceRepository sourceRepository, ModelMapper modelMapper) {
        this.sourceRepository = sourceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SourceResponseDTO save(SourceRequestDTO sourceRequestDTO) {
        Source source = modelMapper.map(sourceRequestDTO, Source.class);
        sourceRepository.save(source);

        return modelMapper.map(source, SourceResponseDTO.class);
    }

    @Override
    public Source findById(Long id) throws Exception {

        return sourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Source no encontrada con id: " + id));
    }

    @Override
    public SourceResponseDTO update(Long id, SourceRequestDTO sourceRequestDTO) throws Exception{
        Source source = findById(id);
        String sourceNameOrigin = sourceRequestDTO.getName();
        source.setName(sourceNameOrigin);
        sourceRepository.save(source);

        return modelMapper.map(source, SourceResponseDTO.class);
    }

    @Override
    public void delete(Long id) throws Exception{
        Source entity = findById(id);
        sourceRepository.delete(entity);
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
