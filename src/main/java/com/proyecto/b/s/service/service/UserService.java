package com.proyecto.b.s.service.service;


import com.proyecto.b.s.entity.User;

import java.util.List;

public interface UserService {
    List<User> listarUsuarios();
    User guardarUsuario(User usuario);
    User actualizarUsuario(User usuario);
    void eliminarUsuario(Long id);
}
