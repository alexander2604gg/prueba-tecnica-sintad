package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteUpdateDto;

import java.util.List;

public interface TipoContribuyenteService {

    List<TipoContribuyenteResponseDto> findAll ();

    TipoContribuyenteResponseDto findById (Integer id);

    TipoContribuyenteResponseDto create (TipoContribuyenteCreateDto tipoContribuyenteCreateDto);

    TipoContribuyenteResponseDto update (Integer id , TipoContribuyenteUpdateDto tipoContribuyenteUpdateDto);

    TipoContribuyenteResponseDto changeEstado(Integer id, boolean status);

}
