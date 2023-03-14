package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.BusquedaRepository;
import com.proyecto.b.s.service.service.BusquedaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusquedaServiceImpl implements BusquedaService {
    private final BusquedaRepository busquedaRepository;

    public BusquedaServiceImpl(BusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    @Override
    public List<Busqueda> listarBusquedas(Cliente cliente, Rol rol, EstadoBusqueda estado, Seniority seniority, String skills){
        if (cliente != null || rol != null || estado != null || seniority != null || skills != null){
            String clienteNombre = cliente != null ? cliente.getNombre() : null;
            String rolName = rol != null ? rol.getName() : null;
            String estadoName = estado != null ? estado.getNombre() : null;
            String seniorityNombre = seniority != null ? seniority.getNombre() : null;
            return busquedaRepository.buscarBusquedas(clienteNombre, rolName, estadoName, seniorityNombre, skills);
        } else {
            return busquedaRepository.findAll().stream()
                    .filter(Busqueda::isActivo)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Busqueda guardarBusqueda(Busqueda busqueda) {
        return busquedaRepository.save(busqueda);
    }

    @Override
    public Optional<Busqueda> buscaPorId(Long id) {
        return busquedaRepository.findById(id);
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
    public boolean existePorId(Long id) {
        return busquedaRepository.existsById(id);
    }

    @Override
    public void eliminarBusqueda(Long id) throws EntityNotFoundException {
        Busqueda entity = busquedaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
        }
        entity.setActivo(false);
    }
}
