package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.*;

import java.util.List;
import java.util.Optional;

public interface SearchService {
    Search saveSearch(Search search);
    Optional<Search> findById(Long id);

    //Search updateSearch(Long id, Search newSearch);
    boolean existById(Long id);
    void deleteSearch(Long id);
    List<Search> listSearch(Client client, Rol rol, StateSearch state, Seniority seniority, List<String> skills);
    List<Search> getSearches(String client, String rol, String state, String seniority, List<String> skills);
}
