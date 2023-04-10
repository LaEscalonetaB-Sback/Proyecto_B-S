package com.proyecto.b.s.controller;
//Cliente -> Client

import com.proyecto.b.s.dto.request.ClientRequestDTO;
import com.proyecto.b.s.dto.response.ClientResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Client;
import com.proyecto.b.s.repository.ClientRepository;
import com.proyecto.b.s.service.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/client")
@CrossOrigin("*")
public class ClientController {

    ClientService clientService;

    ClientRepository clientRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository){
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }




    /**
     * creamos cliente
     * **/
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


    //Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientResponseDTO> deleteById(@PathVariable Long id) throws Exception {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}

