package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.UsuarioRequestDto;
import com.proyecto.b.s.entity.Usuario;

import java.util.List;

public interface UsuarioService{
    List<Usuario> listarUsuarios();
    Usuario guardarUsuario(UsuarioRequestDto usuarioDto) throws Exception;

    Boolean validarEmailDisponible(Usuario usuario)throws Exception;

    Boolean validarContraseña(Usuario Usuario) throws Exception;





}
