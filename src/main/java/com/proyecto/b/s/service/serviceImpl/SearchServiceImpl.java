package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Search;
import com.proyecto.b.s.service.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<Search> listarBusquedas() {
        return null;
    }

    @Override
    public Search guardarBusqueda(Search busqueda) {
        return null;
    }

    @Override
    public Search actualizarBusqueda(Search busqueda) {
        return null;
    }

    @Override
    public void eliminarBusqueda(Long id) {

    }
}
