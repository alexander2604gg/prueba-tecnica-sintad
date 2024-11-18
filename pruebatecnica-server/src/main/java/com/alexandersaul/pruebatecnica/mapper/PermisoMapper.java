package com.alexandersaul.pruebatecnica.mapper;

import com.alexandersaul.pruebatecnica.dto.permiso.PermisoDto;
import com.alexandersaul.pruebatecnica.entity.Permiso;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface PermisoMapper {
    PermisoDto toDto (Permiso permiso);
    Permiso toEntity (PermisoDto permisoDto);
    List<PermisoDto> toDtoList (List<Permiso> permisos);
    List<Permiso> toEntityList(List<PermisoDto> permisosDto);
}
