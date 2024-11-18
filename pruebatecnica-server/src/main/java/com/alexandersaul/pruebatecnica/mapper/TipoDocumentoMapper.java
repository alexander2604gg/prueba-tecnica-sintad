package com.alexandersaul.pruebatecnica.mapper;

import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoResponseDto;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {

    @Mapping(target = "id", source = "idTipoDocumento")
    TipoDocumentoResponseDto toResponseDto(TipoDocumento tipoDocumento);
    List<TipoDocumentoResponseDto> toResponseDtoList (List<TipoDocumento> tipoDocumentoList);
    @Mapping(target = "idTipoDocumento", ignore = true)
    @Mapping(target = "estado", ignore = true)
    TipoDocumento toEntity (TipoDocumentoCreateDto tipoDocumentoCreateDto);

}
