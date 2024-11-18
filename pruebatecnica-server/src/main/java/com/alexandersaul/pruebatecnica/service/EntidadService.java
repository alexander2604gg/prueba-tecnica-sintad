package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.entidad.EntidadCreateDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadUpdateDto;
import org.springframework.data.domain.Page;


public interface EntidadService {

    Page<EntidadResponseDto> findAllWithPagination (int page , int size);

    EntidadResponseDto findById(Integer id);

    EntidadResponseDto findByNumeroDocumento (String nroDocumento);

    EntidadResponseDto create(EntidadCreateDto entidadCreateDto);

    EntidadResponseDto update(Integer id, EntidadUpdateDto entidadUpdateDto);

    EntidadResponseDto changeEstado(Integer id, boolean status);

}
