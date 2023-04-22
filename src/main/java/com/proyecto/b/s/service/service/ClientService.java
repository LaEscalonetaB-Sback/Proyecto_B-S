package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.ClientRequestDTO;
import com.proyecto.b.s.dto.response.ClientResponseDTO;
import com.proyecto.b.s.entity.Client;

import java.util.List;

public interface ClientService {
    Client findById(Long id) throws Exception;

    List<ClientResponseDTO> searchClient(String name, Integer cuit);

    Client saveClient(ClientRequestDTO clientRequestDTO);


    void deleteClient(Long id) throws Exception;
}
