package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteUpdateDto;
import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.TIpoContribuyenteMapper;
import com.alexandersaul.pruebatecnica.repository.TipoContribuyenteRepository;
import com.alexandersaul.pruebatecnica.service.TipoContribuyenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContribuyenteServiceImpl implements TipoContribuyenteService {

    @Autowired
    private TipoContribuyenteRepository tipoContribuyenteRepository;
    @Autowired
    private TIpoContribuyenteMapper tipoContribuyenteMapper;

    @Override
    public List<TipoContribuyenteResponseDto> findAll() {
        List<TipoContribuyente> tipoContribuyente = tipoContribuyenteRepository.findActiveTipoContribuyentes();
        return tipoContribuyenteMapper.toResponseDtoList(tipoContribuyente);
    }

    @Override
    public TipoContribuyenteResponseDto findById(Integer id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoContribuyente" , "id" , id.toString())
        );
        return tipoContribuyenteMapper.toResponseDto(tipoContribuyente);
    }

    @Override
    public TipoContribuyenteResponseDto create(TipoContribuyenteCreateDto tipoContribuyenteCreateDto) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteMapper.toEntity(tipoContribuyenteCreateDto);
        tipoContribuyente.setEstado(true);
        return tipoContribuyenteMapper.toResponseDto(tipoContribuyenteRepository.save(tipoContribuyente));
    }

    @Override
    public TipoContribuyenteResponseDto update(Integer id, TipoContribuyenteUpdateDto tipoContribuyenteUpdateDto) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoContribuyente" , "id" , id.toString())
        );
        tipoContribuyente.setNombre(tipoContribuyenteUpdateDto.getNombre());
        return tipoContribuyenteMapper.toResponseDto(tipoContribuyenteRepository.saveAndFlush(tipoContribuyente));
    }

    @Override
    public TipoContribuyenteResponseDto changeEstado(Integer id, boolean status) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoContribuyente" , "id" , id.toString())
        );
        tipoContribuyente.setEstado(status);
        return tipoContribuyenteMapper.toResponseDto(tipoContribuyenteRepository.saveAndFlush(tipoContribuyente));
    }
}
