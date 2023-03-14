package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> listarClientes();
    Client guardarCliente(Client client);
    Client actualizarCliente(Client client);
    void eliminarCliente(Long id);
}
