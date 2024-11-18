package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.rol.RolDto;

import java.util.List;

public interface RolService {
    List<RolDto> findAll();
    RolDto findById (Integer id);
    RolDto create (RolDto rolDto);
    RolDto update ( Integer id , RolDto rolDto);
    void delete (Integer id);
}
