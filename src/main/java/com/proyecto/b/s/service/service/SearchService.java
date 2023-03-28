package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.SearchRequestDto;
import com.proyecto.b.s.dto.response.SearchResponseDto;
import com.proyecto.b.s.entity.*;

import java.util.List;

public interface SearchService {
    Search saveSearch(SearchRequestDto searchRequestDto);
    Search findById(Long id) throws Exception;
    Search update(Search newSearch) throws Exception;
    boolean existById(Long id);
    void deleteSearch(Long id);
    List<SearchResponseDto> listSearch(String client, String rol, String state, String seniority, List<String> skills);
}
