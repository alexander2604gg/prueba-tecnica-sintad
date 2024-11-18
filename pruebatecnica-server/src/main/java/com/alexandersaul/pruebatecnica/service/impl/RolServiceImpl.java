package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.rol.RolDto;
import com.alexandersaul.pruebatecnica.entity.Permiso;
import com.alexandersaul.pruebatecnica.entity.Rol;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.RolMapper;
import com.alexandersaul.pruebatecnica.repository.PermisoRepository;
import com.alexandersaul.pruebatecnica.repository.RolRepository;
import com.alexandersaul.pruebatecnica.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private RolMapper rolMapper;

    @Override
    public List<RolDto> findAll() {
        List<Rol> roles = rolRepository.findAll();
        return rolMapper.toDtoList(roles);
    }

    @Override
    public RolDto findById(Integer id) {
        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Rol", "id", id.toString())
        );
        return rolMapper.toDto(rol);
    }

    @Override
    public RolDto create(RolDto rolDto) {
        Rol rol = rolMapper.toEntity(rolDto);
        List<Permiso> permisos = rolDto.getPermisos().stream()
                .map(permisoDto -> permisoRepository.findById(permisoDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Permiso", "id", permisoDto.getId().toString()))
                )
                .collect(Collectors.toList());

        rol.setPermisos(permisos);
        return rolMapper.toDto(rolRepository.save(rol));
    }

    @Override
    public RolDto update(Integer id, RolDto rolDto) {

        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Rol", "id", id.toString())
        );

        List<Permiso> permisos = rolDto.getPermisos().stream()
                .map(permisoDto -> permisoRepository.findById(permisoDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Permiso", "id", permisoDto.getId().toString()))
                )
                .collect(Collectors.toList());

        rol.setPermisos(permisos);
        rol.setNombre(rolDto.getNombre());

        return rolMapper.toDto(rolRepository.save(rol));
    }

    @Override
    public void delete(Integer id) {
        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Rol" , "id" , id.toString())
        );
        rolRepository.delete(rol);
    }
}
