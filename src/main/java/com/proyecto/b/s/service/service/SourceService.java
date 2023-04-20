package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.SourceRequestDTO;
import com.proyecto.b.s.dto.response.SourceResponseDTO;
import com.proyecto.b.s.entity.Source;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SourceService {
    SourceResponseDTO save(SourceRequestDTO sourceRequestDTO);

    Source findById(Long id) throws Exception;

    SourceResponseDTO update(Long searchId, SourceRequestDTO sourceRequestDTO) throws EntityNotFoundException;

    void delete(Long id);

    List<SourceResponseDTO> list();

    boolean existById(Long id);
}