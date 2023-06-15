package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO user) {
        UserResponseDTO result;
        try {
            result = userService.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(result);
    }

    //Listar usuarios
    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> ListUsers() {
        List<UserResponseDTO> users = userService.userList();

        return ResponseEntity.ok(users);
    }
}