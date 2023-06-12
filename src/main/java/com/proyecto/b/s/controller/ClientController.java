package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.ClientRequestDTO;
import com.proyecto.b.s.dto.response.ClientResponseDTO;
import com.proyecto.b.s.entity.Client;
import com.proyecto.b.s.service.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/client")
@CrossOrigin("*")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody ClientRequestDTO clientRequestDto) throws Exception {
        Client result = clientService.saveClient(clientRequestDto);

        return ResponseEntity.ok(result);
    }

    //Lista de clientes
    @GetMapping("/list")
    public ResponseEntity<List<ClientResponseDTO>> searchClient(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cuit", required = false) Integer cuit) {
        List<ClientResponseDTO> clients = clientService.searchClient(name, cuit);

        return ResponseEntity.ok(clients);
    }

    //Actualizar skill
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) throws Exception {
        ClientResponseDTO result = clientService.update(id, clientRequestDTO);

        return ResponseEntity.ok(result);
    }

    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientResponseDTO> deleteById(@PathVariable Long id) throws Exception {
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}