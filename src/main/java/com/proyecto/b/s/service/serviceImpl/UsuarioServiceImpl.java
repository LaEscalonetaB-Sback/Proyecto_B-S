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
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return null;
    }

    @Override
    public Usuario guardarUsuario(UsuarioRequestDto usuarioDTO) {
        Usuario usuario = new Usuario(null,
                usuarioDTO.getEmail(),
                usuarioDTO.getClave(),
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido());

        System.out.println(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Boolean validarEmailDisponible(Usuario usuario) throws Exception {
        Optional<Usuario> usuarioEncontrado = Optional.ofNullable(usuarioRepository.findByEmail(usuario.getEmail()));
                if(usuarioEncontrado.isPresent()){
                  //  throw new UserFoundException("Mail: " + usuario.getId() + "no esta disponible");
                }
                return true;
    }

    @Override
    public Boolean validarContraseña(Usuario usuario) throws Exception {
        if(usuario.getClave() == null || usuario.getClave().isEmpty()){
            System.out.println("La contraseña es obligatoria");
        }
        if(!usuario.getClave().equals(usuario.getClave())){
            System.out.println("El mail y la contraseña no coinciden");
        }

        return true;
    }


    /**
    @Override
    public UserDetails entrarPorMail(String email) throws UsernameNotFoundException {
        Usuario usuario = personaRepository.findByEmail(email);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new Usuario(usuario.getEmail(),usuario.getClave());
    }

**/
}
