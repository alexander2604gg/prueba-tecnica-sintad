package com.alexandersaul.pruebatecnica.repository;

import com.alexandersaul.pruebatecnica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario , Integer> {
    Optional<Usuario> findUsuarioByNombreUsuario (String userName);
}
