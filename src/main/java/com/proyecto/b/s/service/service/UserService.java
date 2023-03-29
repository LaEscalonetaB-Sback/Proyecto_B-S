package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.UserRequestDto;
import com.proyecto.b.s.entity.User;

import java.util.List;

public interface UserService {
    List<User> userList();
    User createUser(UserRequestDto userDto) throws Exception;
    Boolean validationEmail(UserRequestDto userDto)throws Exception;
    Boolean validationPassword(UserRequestDto userDto) throws Exception;
}
