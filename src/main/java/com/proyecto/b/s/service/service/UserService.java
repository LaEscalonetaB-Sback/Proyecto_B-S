package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> userList();

    UserResponseDTO createUser(UserRequestDTO userDto) throws Exception;

    Boolean validationEmail(UserRequestDTO userDto) throws Exception;

    Boolean validationPassword(UserRequestDTO userDto) throws Exception;

    User findById(Long id) throws Exception;

    User findByName(String name);
}
