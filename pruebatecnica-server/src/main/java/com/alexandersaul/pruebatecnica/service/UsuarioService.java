package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.usuario.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDto> findAll();
    UsuarioDto findById (Integer id);
    UsuarioDto create (UsuarioDto usuarioDto);
    UsuarioDto update (Integer id , UsuarioDto usuarioDto );
    void delete (Integer id);
    public String encryptPassword(String password);
}
