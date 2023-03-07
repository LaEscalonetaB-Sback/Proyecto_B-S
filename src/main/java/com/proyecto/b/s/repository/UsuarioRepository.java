package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

     public Usuario findByEmail(String email);
}
