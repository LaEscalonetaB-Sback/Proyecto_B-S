package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.UserRequestDto;
import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    /**
     * Devuelve todos los usuarios de la base de datos
     * @return List<Usuario>
     */
    @GetMapping("/bys/users")
    // @Operation(description = "Listar todos los usuarios de la base de datos")
    public List<User> listarTodasLosUsuarios(){
        return userService.userList();

    }

    /**
     * Recibo un usuario y lo guardo en la base de datos
     * @param userDto
     * @return Usuario
     **/
    @PostMapping("/bys/registro")
    // @Operation(description = "Recibir un usuario y guardarlo en la base de datos")
    public User crearUsuario(@RequestBody UserRequestDto userDto) throws Exception {
        return userService.createUser(userDto);
    }
}
