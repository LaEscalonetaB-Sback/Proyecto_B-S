package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.IndustryRequestDTO;
import com.proyecto.b.s.dto.response.IndustryResponseDTO;
import com.proyecto.b.s.entity.Industry;

import java.util.List;

public interface IndustryService {
    Industry findById(Long id) throws Exception;

    boolean existById(Long id);

    List<IndustryResponseDTO> listIndustry();

    IndustryResponseDTO saveIndustry(IndustryRequestDTO industryRequestDTO);

    IndustryResponseDTO updateIndustry(Long id, IndustryRequestDTO industryRequestDTO) throws Exception;

    void deleteIndustry(Long id) throws Exception;
    public Industry findByName(String name);
}
