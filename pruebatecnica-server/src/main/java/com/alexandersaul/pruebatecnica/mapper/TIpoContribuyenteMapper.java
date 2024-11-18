package com.alexandersaul.pruebatecnica.mapper;


import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteResponseDto;
import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TIpoContribuyenteMapper {

    @Mapping(target = "id", source = "idTipoContribuyente")
    TipoContribuyenteResponseDto toResponseDto(TipoContribuyente tipoContribuyente);
    List<TipoContribuyenteResponseDto> toResponseDtoList (List<TipoContribuyente> tipoContribuyenteList);
    @Mapping(target = "idTipoContribuyente", ignore = true)
    @Mapping(target = "estado", ignore = true)
    TipoContribuyente toEntity (TipoContribuyenteCreateDto tipoContribuyenteCreateDto);

}
