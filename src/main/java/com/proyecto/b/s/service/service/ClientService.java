package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> listClient();
    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
}
