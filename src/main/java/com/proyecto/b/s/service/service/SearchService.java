package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.searchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.Search;

import java.util.List;

public interface SearchService {
    SearchResponseDTO saveSearch(SearchRequestDTO searchRequestDto) throws Exception;

    Search findById(Long id) throws Exception;

    SearchResponseDTO update(Long searchId, SearchRequestDTO searchRequestDto) throws Exception;
    public SearchResponseDTO updateSearchState (Long searchId) throws Exception;

    boolean existById(Long id);

    void deleteSearch(Long id) throws Exception;

    List<SearchResponseDTO> listSearch(String client, String rol, String state, List<String> seniority, List<String> skills);

    Search findByName(String name);

    List<SearchResponseDTO> listAllActive();
}
