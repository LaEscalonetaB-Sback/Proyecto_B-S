package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bs/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Listar usuarios
    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> ListUsers() {
        List<UserResponseDTO> users = userService.userList();

        return ResponseEntity.ok(users);
    }
}
