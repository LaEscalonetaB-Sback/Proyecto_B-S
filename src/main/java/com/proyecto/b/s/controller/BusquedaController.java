package com.proyecto.b.s.controller;

import com.proyecto.b.s.entity.Busqueda;
import com.proyecto.b.s.service.service.BusquedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bs/busqueda")
public class BusquedaController {
    private final BusquedaService busquedaService;

    public BusquedaController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    //CRUD
    //Lista de busquedas
    @GetMapping("/lista")
    public List<Busqueda> findAll(@RequestParam(required = false) String cliente,
                                  @RequestParam(required = false) String rol,
                                  @RequestParam(required = false) String estado){
        return busquedaService.listarBusquedas(cliente, rol, estado);
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
