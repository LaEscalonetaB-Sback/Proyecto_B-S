package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.SearchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SearchService {
    SearchResponseDTO saveSearch(SearchRequestDTO searchRequestDto);
    Search findById(Long id) throws Exception;
    SearchResponseDTO update(Long searchId, SearchRequestDTO searchRequestDto) throws EntityNotFoundException;
    boolean existById(Long id);
    void deleteSearch(Long id);
    List<SearchResponseDTO> listSearch(String client, String rol, String state, List<String> seniority, List<String> skills);
}
