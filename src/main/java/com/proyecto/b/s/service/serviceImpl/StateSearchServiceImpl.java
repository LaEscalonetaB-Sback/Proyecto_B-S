package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.StateSearch;
import com.proyecto.b.s.repository.StateSearchRepository;
import com.proyecto.b.s.service.service.StateSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateSearchServiceImpl implements StateSearchService {
    private final StateSearchRepository stateSearchRepository;

    public StateSearchServiceImpl(StateSearchRepository stateSearchRepository) {
        this.stateSearchRepository = stateSearchRepository;
    }

    @Override
    public List<StateSearch> list() {

        return stateSearchRepository.findAll();
    }

    @Override
    public StateSearch findByName(String name) {

        return stateSearchRepository.findByName(name);
    }
}
