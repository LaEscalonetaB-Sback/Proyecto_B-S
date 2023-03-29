package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.UserRequestDto;

import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.b.s.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> userList() {
        return null;
    }

    @Override
    public User createUser(UserRequestDto userDto) throws Exception {
        return null;
    }

    @Override
    public Boolean validationEmail(UserRequestDto userDto) throws Exception {
        return null;
    }

    @Override
    public Boolean validationPassword(UserRequestDto userDto) throws Exception {
        return null;
    }
}
