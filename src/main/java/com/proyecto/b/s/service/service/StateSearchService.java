package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.StateSearch;

import java.util.List;

public interface StateSearchService {
    List<StateSearch> list();

    StateSearch findByName(String name);
}
