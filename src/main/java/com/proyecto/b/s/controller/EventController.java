package com.proyecto.b.s.controller;

import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.ClienteRepository;
import com.proyecto.b.s.repository.EstadoRepository;
import com.proyecto.b.s.repository.RolRepository;
import com.proyecto.b.s.repository.SeniorityRepository;
import com.proyecto.b.s.service.service.BusquedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/busqueda")
public class BusquedaController {
    private final BusquedaService busquedaService;
    private final ClienteRepository clienteRepository;
    private final RolRepository rolRepository;
    private final EstadoRepository estadoRepository;
    private final SeniorityRepository seniorityRepository;
    public BusquedaController(BusquedaService busquedaService, ClienteRepository clienteRepository, RolRepository rolRepository, EstadoRepository estadoRepository, SeniorityRepository seniorityRepository) {
        this.busquedaService = busquedaService;
        this.clienteRepository = clienteRepository;
        this.rolRepository = rolRepository;
        this.estadoRepository = estadoRepository;
        this.seniorityRepository = seniorityRepository;
    }

    //CRUD
    //Lista de busquedas
    @GetMapping("/lista")
    public ResponseEntity<List<Busqueda>> buscarBusquedas(
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) String skills) {
        Cliente clienteObj = null;
        Rol rolObj = null;
        EstadoBusqueda estadoObj = null;
        Seniority seniorityObj = null;

        if (cliente != null) {
            clienteObj = clienteRepository.findByNombre(cliente);
        }
        if (rol != null) {
            rolObj = rolRepository.findByNombre(rol);
        }
        if (estado != null) {
            estadoObj = estadoRepository.findByNombre(estado);
        }
        if (seniority != null) {
            seniorityObj = seniorityRepository.findByNombre(seniority);
        }

        List<Busqueda> busquedas = busquedaService.listarBusquedas(clienteObj, rolObj, estadoObj, seniorityObj, skills);

        if (busquedas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(busquedas);
        }
    }

    //Encuentra busqueda por id
    @GetMapping("/{id}")
    public ResponseEntity<Busqueda> findOne(@PathVariable Long id){
        Optional<Busqueda> busquedaOpt = busquedaService.buscaPorId(id);
        return busquedaOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear busqueda
    @PostMapping("/crear")
    public ResponseEntity<Busqueda> create(@RequestBody Busqueda busqueda){
        if (busqueda.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Busqueda result = busquedaService.guardarBusqueda(busqueda);
        return ResponseEntity.ok(result);
    }

    //Actualizar busqueda
    @PutMapping("/actualizar")
    public ResponseEntity<Busqueda> update(@RequestBody Busqueda busqueda){
        if (busqueda.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!busquedaService.existePorId(busqueda.getId())){
            return ResponseEntity.notFound().build();
        }
        Busqueda result = busquedaService.guardarBusqueda(busqueda);
        return ResponseEntity.ok(result);
    }

    //Eliminar busqueda por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Busqueda> delete(@PathVariable Long id){
        busquedaService.eliminarBusqueda(id);
        return ResponseEntity.noContent().build();
    }
}
