package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.SearchRepository;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;

    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }


    @Override
    public List<Search> listSearch(Client client, Rol rol, StateSearch state, Seniority seniority, String skills){
        if (client != null || rol != null || state != null || seniority != null || skills != null){
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

    /**
     @Override
     public Busqueda actualizarBusqueda(Long id, String estado, int vacante) {
     Busqueda entity = busquedaRepository.findById(id).orElse(null);
     if (entity != null) {
     entity.setEstado(estado);
     entity.setVacantes(vacante);
     busquedaRepository.save(entity);
     }
     return entity;
     }**/

    @Override
    public void deleteSearch(Long id) throws EntityNotFoundException {
        Search entity = searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
        }
        entity.setActive(false);
    }
}
