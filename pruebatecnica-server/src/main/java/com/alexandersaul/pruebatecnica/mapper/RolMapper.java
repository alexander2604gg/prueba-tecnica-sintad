package com.alexandersaul.pruebatecnica.mapper;

import com.alexandersaul.pruebatecnica.dto.rol.RolDto;
import com.alexandersaul.pruebatecnica.entity.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDto toDto (Rol rol);
    Rol toEntity (RolDto rolDto);
    List<RolDto> toDtoList (List<Rol> roles);
    List<Rol> toEntityList(List<RolDto> rolDtos);

}
