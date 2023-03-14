package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> listarUsuarios() {
        return null;
    }

    @Override
    public User guardarUsuario(User usuario) {
        return null;
    }

    @Override
    public User actualizarUsuario(User usuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {

    }
}
