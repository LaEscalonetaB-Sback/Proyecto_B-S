package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.entity.User;

import java.util.List;

public interface UserService {
    List<User> userList();
    UserResponseDTO createUser(UserRequestDTO userDto) throws Exception;
    Boolean validationEmail(UserRequestDTO userDto)throws Exception;
    Boolean validationPassword(UserRequestDTO userDto) throws Exception;
}
