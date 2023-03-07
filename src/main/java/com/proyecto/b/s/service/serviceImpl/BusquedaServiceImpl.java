package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Busqueda;
import com.proyecto.b.s.repository.BusquedaRepository;
import com.proyecto.b.s.service.service.BusquedaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusquedaServiceImpl implements BusquedaService {
    private final BusquedaRepository busquedaRepository;

    public BusquedaServiceImpl(BusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    @Override
    public List<Busqueda> listarBusquedas(String cliente, String rol, String estado){
        return switch (tipoBusqueda(cliente, rol, estado)) {
            case "clienteRolEstado" -> busquedaRepository.findByClienteAndRolAndEstadoBusquedas(cliente, rol, estado);
            case "clienteRol" -> busquedaRepository.findByClienteAndRol(cliente, rol);
            case "clienteEstado" -> busquedaRepository.findByClienteAndEstadoBusquedas(cliente, estado);
            case "rolEstado" -> busquedaRepository.findByRolAndEstadoBusquedas(rol, estado);
            case "cliente" -> busquedaRepository.findByCliente(cliente);
            case "rol" -> busquedaRepository.findByRol(rol);
            case "estado" -> busquedaRepository.findByEstadoBusquedas(estado);
            default -> busquedaRepository.findAll().stream().filter(Busqueda::isActivo).collect(Collectors.toList());
        };
    }
    private String tipoBusqueda(String cliente, String rol, String estado) {
        if (cliente != null && rol != null && estado != null) {
            return "clienteRolEstado";
        } else if (cliente != null && rol != null) {
            return "clienteRol";
        } else if (cliente != null && estado != null) {
            return "clienteEstado";
        } else if (rol != null && estado != null) {
            return "rolEstado";
        } else if (cliente != null) {
            return "cliente";
        } else if (rol != null) {
            return "rol";
        } else if (estado != null) {
            return "estado";
        } else {
            return "todos";
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

    @Override
    public List<Busqueda> encontrarPorNombre(String nombre) {
        return busquedaRepository.findByCliente(nombre);
    }

    @Override
    public List<Busqueda> encocontrarPorRol(String rol) {
        busquedaRepository.findByRol(rol);
        return busquedaRepository.findByRol(rol);
    }

    @Override
    public List<Busqueda> encontrarPorEstado(String estado) {
        return busquedaRepository.findByEstadoBusquedas(estado);
    }
}
