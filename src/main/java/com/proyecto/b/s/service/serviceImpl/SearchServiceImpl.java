package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.*;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final ClientRepository clientRepository;
    private final RolRepository rolRepository;
    private final StateRepository stateRepository;
    private final SeniorityRepository seniorityRepository;

    public SearchServiceImpl(SearchRepository searchRepository, ClientRepository clientRepository, RolRepository rolRepository, StateRepository stateRepository, SeniorityRepository seniorityRepository) {
        this.searchRepository = searchRepository;
        this.clientRepository = clientRepository;
        this.rolRepository = rolRepository;
        this.stateRepository = stateRepository;
        this.seniorityRepository = seniorityRepository;
    }

    @Override
    public List<Search> listSearch(Client client, Rol rol, StateSearch state, Seniority seniority, List<String> skills){
        if (client != null || rol != null || state != null || seniority != null || (skills != null && !skills.isEmpty())){
            String clientName = client != null ? client.getName() : null;
            String rolName = rol != null ? rol.getName() : null;
            String stateName = state != null ? state.getName() : null;
            String seniorityName = seniority != null ? seniority.getName() : null;
            return searchRepository.findSearchBy(clientName, rolName, stateName, seniorityName, skills);
        } else {
            return searchRepository.findAll().stream()
                    .filter(Search::isActive)
                    .collect(Collectors.toList());
        }
    }

    public List<Search> getSearches(String client, String rol, String state, String seniority, List<String> skills) {
        Client clientObj = null;
        Rol rolObj = null;
        StateSearch stateObj = null;
        Seniority seniorityObj = null;

        if (client != null) {
            clientObj = clientRepository.findByName(client);
        }
        if (rol != null) {
            rolObj = rolRepository.findByName(rol);
        }
        if (state != null) {
            stateObj = stateRepository.findByName(state);
        }
        if (seniority != null) {
            seniorityObj = seniorityRepository.findByName(seniority);
        }

        List<Search> search = listSearch(clientObj, rolObj, stateObj, seniorityObj, skills);
        return search;
    }

    @Override
    public Search saveSearch(Search search) {
        return searchRepository.save(search);
    }

    @Override
    public Optional<Search> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existById(Long id) {
        return searchRepository.existsById(id);
    }


     /*@Override
     public Search updateSearch(Long id, Search newSearch) throws EntityNotFoundException {
     Search existingSearch = searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));

     if (existingSearch == null) {
     throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
     }

     // Actualizar las propiedades de la búsqueda existente con los valores de la nueva búsqueda
     existingSearch.setActive(newSearch.isActive());
     existingSearch.setRol(newSearch.getRol());
     existingSearch.setSeniority(newSearch.getSeniority());

     // Guardar los cambios
     Search updatedSearch = searchRepository.saveSearch(existingSearch);

     return updatedSearch;
     }*/

    @Override
    public void deleteSearch(Long id) throws EntityNotFoundException {
        Search entity = searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
        }
        entity.setActive(false);
    }
}
