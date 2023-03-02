package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(Long id);
}
