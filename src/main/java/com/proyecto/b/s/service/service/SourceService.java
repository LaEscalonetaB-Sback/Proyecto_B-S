package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.SourceRequestDTO;
import com.proyecto.b.s.dto.response.SourceResponseDTO;
import com.proyecto.b.s.entity.Source;

import java.util.List;

public interface SourceService {
    SourceResponseDTO save(SourceRequestDTO sourceRequestDTO);

    Source findById(Long id) throws Exception;

    SourceResponseDTO update(Long searchId, SourceRequestDTO sourceRequestDTO) throws Exception;

    void delete(Long id) throws Exception;

    List<SourceResponseDTO> list();

    boolean existById(Long id);

    Source findByName(String name);
}
