package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Busqueda;
import com.proyecto.b.s.repository.BusquedaRepository;
import com.proyecto.b.s.service.service.BusquedaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusquedaServiceImpl implements BusquedaService {
    private final BusquedaRepository busquedaRepository;

    public BusquedaServiceImpl(BusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    @Override
    public List<Busqueda> listarBusquedas() {
        return busquedaRepository.findAll();
    }

    @Override
    public Busqueda guardarBusqueda(Busqueda busqueda) {
        return busquedaRepository.save(busqueda);
    }

    @Override
    public Optional<Busqueda> buscaPorId(Long id) {
        return busquedaRepository.findById(id);
    }

    @Override
    public Busqueda actualizarBusqueda(Long id, String estado, String vacante) {
        Busqueda entity = busquedaRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setEstado(estado);
            entity.setVacantes(vacante);
            busquedaRepository.save(entity);
        }
        return entity;
    }

    @Override
    public boolean existePorId(Long id) {
        return busquedaRepository.existsById(id);
    }

    @Override
    public void eliminarBusqueda(Long id) throws EntityNotFoundException {
        Busqueda entity = busquedaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
        }
        busquedaRepository.delete(entity);
    }
}
