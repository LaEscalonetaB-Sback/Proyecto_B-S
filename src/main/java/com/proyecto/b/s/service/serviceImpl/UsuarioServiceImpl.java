package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.UsuarioRequestDto;
import com.proyecto.b.s.entity.Usuario;
import com.proyecto.b.s.repository.UsuarioRepository;
import com.proyecto.b.s.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
       return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(UsuarioRequestDto usuarioDTO) {
        Usuario usuario =  new Usuario(null,
                usuarioDTO.getEmail(),
                usuarioDTO.getClave(),
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido());

        System.out.println(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Boolean validarEmailDisponible(UsuarioRequestDto usuarioDto) throws Exception {
        Optional<Usuario> usuarioEncontrado = Optional.ofNullable(usuarioRepository.findByEmail(usuarioDto.getEmail()));
                if(usuarioEncontrado.isPresent()){
                  //  throw new UserFoundException("Mail: " + usuario.getId() + "no esta disponible");
                }
                return true;
    }

    @Override
    public Boolean validarContraseña(UsuarioRequestDto usuarioDto) throws Exception {
        if(usuarioDto.getClave() == null || usuarioDto.getClave().isEmpty()){
            System.out.println("La contraseña es obligatoria");
        }
        if(!usuarioDto.getClave().equals(usuarioDto.getClave())){
            System.out.println("El mail y la contraseña no coinciden");
        }

        return true;
    }

    /**

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null) {
            System.out.println("Usuario o password inválidos");
        }

        return new User(usuario.getEmail(),usuario.getClave(),mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<RolUsuario> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

**/
}
