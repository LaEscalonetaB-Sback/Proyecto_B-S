package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.service.service.UserService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> userList() {
        List<User> users = userRepository.findAll();
        HelperValidator.isEmptyList(users);
        return users;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDto) {
        User users = modelMapper.map(userDto, User.class);
        User userSave = userRepository.save(users);
        return modelMapper.map(userSave, UserResponseDTO.class);
    }

    @Override
    public Boolean validationEmail(UserRequestDTO userDto) {
        return null;
    }

    @Override
    public Boolean validationPassword(UserRequestDTO userDto) {
        return null;
    }
}
