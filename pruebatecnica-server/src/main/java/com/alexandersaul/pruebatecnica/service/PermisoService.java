package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.permiso.PermisoDto;

import java.util.List;

public interface PermisoService {
    List<PermisoDto> findAll();
    PermisoDto create(PermisoDto permisoDto);
    PermisoDto update (Integer id , PermisoDto permisoDto);
    PermisoDto findById(Integer id);
    void delete(Integer id);
}
