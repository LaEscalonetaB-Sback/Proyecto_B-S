package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.UsuarioRequestDto;
import com.proyecto.b.s.entity.Usuario;
import com.proyecto.b.s.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    /**
     * Devuelve todos los usuarios de la base de datos
     * @return List<Usuario>
     */
    @GetMapping("/bys/usuarios")
    // @Operation(description = "Listar todos los usuarios de la base de datos")
    public List<Usuario> listarTodasLosUsuarios(){
        return usuarioService.listarUsuarios();

    }

    /**
     * Recibo un usuario y lo guardo en la base de datos
     * @param usuarioDto
     * @return Usuario
     **/
    @PostMapping("/bys/registro")
    // @Operation(description = "Recibir un usuario y guardarlo en la base de datos")
    public Usuario crearUsuario(@RequestBody UsuarioRequestDto usuarioDto) throws Exception {
        return usuarioService.guardarUsuario(usuarioDto);
    }
}
