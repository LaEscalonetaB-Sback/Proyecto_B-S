package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.StateSearch;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.StateSearchRepository;
import com.proyecto.b.s.service.service.StateSearchService;
import com.proyecto.b.s.utils.HelperValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateSearchServiceImpl implements StateSearchService {
    private final StateSearchRepository stateSearchRepository;

    public StateSearchServiceImpl(StateSearchRepository stateSearchRepository) {
        this.stateSearchRepository = stateSearchRepository;
    }

    @Override
    public List<StateSearch> list() {
        List<StateSearch> stateSearch = stateSearchRepository.findAll();
        HelperValidator.isEmptyList(stateSearch);

        return stateSearch;
    }

    @Override
    public StateSearch findByName(String name) {

        return Optional.ofNullable(stateSearchRepository.findByName(name))
                .orElseThrow(()-> new InvalidResourceException("Opcion no encontrada con el nombre " + name + "."));
    }
}
