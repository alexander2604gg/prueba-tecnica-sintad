package com.alexandersaul.pruebatecnica.mapper;

import com.alexandersaul.pruebatecnica.dto.entidad.EntidadCreateDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import com.alexandersaul.pruebatecnica.entity.Entidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntidadMapper {

    @Mapping(target = "id", source = "idEntidad")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento.nombre")
    @Mapping(target = "tipoContribuyente", source = "tipoContribuyente.nombre")
    EntidadResponseDto toResponseDto(Entidad entity);
    List<EntidadResponseDto> toResponseDtoList (List<Entidad> entidadList);
    @Mapping(target = "idEntidad", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "tipoContribuyente", ignore = true )
    Entidad toEntity (EntidadCreateDto entidadCreateDto);

}
