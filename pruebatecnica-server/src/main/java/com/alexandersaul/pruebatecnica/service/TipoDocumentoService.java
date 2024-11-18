package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoUpdateDto;

import java.util.List;

public interface TipoDocumentoService {

    List<TipoDocumentoResponseDto> findAll ();

    TipoDocumentoResponseDto findById (Integer id);

    TipoDocumentoResponseDto create (TipoDocumentoCreateDto tipoDocumentoCreateDto);

    TipoDocumentoResponseDto update (Integer id , TipoDocumentoUpdateDto tipoDocumentoUpdateDto);

    TipoDocumentoResponseDto changeEstado(Integer id, boolean status);


}
