package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.usuario.UsuarioDto;
import com.alexandersaul.pruebatecnica.entity.Rol;
import com.alexandersaul.pruebatecnica.entity.Usuario;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.UsuarioMapper;
import com.alexandersaul.pruebatecnica.repository.RolRepository;
import com.alexandersaul.pruebatecnica.repository.UsuarioRepository;
import com.alexandersaul.pruebatecnica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }

    @Override
    public UsuarioDto findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario" , "id" , id.toString())
        );
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto create(UsuarioDto usuarioDto) {
        usuarioDto.setContrasena(encryptPassword(usuarioDto.getContrasena()));
        List<Rol> roles = usuarioDto.getRoles().stream()
                .map(rolDto -> rolRepository.findById(rolDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", rolDto.getId().toString()))
                )
                .collect(Collectors.toList());

        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        usuario.setRoles(roles);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto update(Integer id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", id.toString())
        );

        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setHabilitado(usuarioDto.isHabilitado());

        List<Rol> roles = usuarioDto.getRoles().stream()
                .map(rolDto -> rolRepository.findById(rolDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", rolDto.getId().toString()))
                )
                .collect(Collectors.toList());
        usuario.setRoles(roles);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public void delete(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", id.toString())
        );

        usuarioRepository.delete(usuario);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
