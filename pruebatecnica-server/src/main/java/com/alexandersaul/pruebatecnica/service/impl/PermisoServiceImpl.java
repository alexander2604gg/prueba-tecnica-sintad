package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.permiso.PermisoDto;
import com.alexandersaul.pruebatecnica.entity.Permiso;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.PermisoMapper;
import com.alexandersaul.pruebatecnica.repository.PermisoRepository;
import com.alexandersaul.pruebatecnica.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private PermisoMapper permisoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PermisoDto> findAll() {
        List<Permiso> permisos = permisoRepository.findAll();
        return permisoMapper.toDtoList(permisos);
    }

    @Transactional
    @Override
    public PermisoDto create(PermisoDto permisoDto) {
        Permiso permiso = permisoRepository.save(permisoMapper.toEntity(permisoDto));
        return permisoMapper.toDto(permiso);
    }

    @Transactional
    @Override
    public PermisoDto update(Integer id, PermisoDto permisoDto) {
        Permiso permiso = permisoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permiso" , "id" , id.toString())
        );
        permiso.setNombre(permisoDto.getNombre());
        return permisoMapper.toDto(permisoRepository.save(permiso));
    }

    @Transactional(readOnly = true)
    @Override
    public PermisoDto findById(Integer id) {
        Permiso permiso = permisoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permiso" , "id" , id.toString())
        );
        return permisoMapper.toDto(permiso);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Permiso permiso = permisoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permiso", "id"  , id.toString())
        );
        permisoRepository.delete(permiso);
    }
}
