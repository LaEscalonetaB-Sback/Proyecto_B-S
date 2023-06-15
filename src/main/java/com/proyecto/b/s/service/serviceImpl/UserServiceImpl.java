package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.service.service.UserService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponseDTO> userList() {
        List<User> users = userRepository.findAll();
        HelperValidator.isEmptyList(users);

        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDto) {
        User users = modelMapper.map(userDto, User.class);
        User userSave = userRepository.save(users);

        return modelMapper.map(userSave, UserResponseDTO.class);
    }

    @Override
    public User findById(Long id) throws Exception {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrada con id: " + id));
    }

    @Override
    public User findByName(String name) {

        return Optional.ofNullable(userRepository.findByName(name))
                .orElseThrow(() -> new InvalidResourceException("Usuario no encontrado con el nombre " + name + "."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
