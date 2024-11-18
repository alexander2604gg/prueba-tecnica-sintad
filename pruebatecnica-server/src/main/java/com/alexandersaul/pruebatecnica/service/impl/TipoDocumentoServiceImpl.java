package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoUpdateDto;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.TipoDocumentoMapper;
import com.alexandersaul.pruebatecnica.repository.TipoDocumentoRepository;
import com.alexandersaul.pruebatecnica.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    @Autowired
    public TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    public TipoDocumentoMapper tipoDocumentoMapper;

    @Override
    public List<TipoDocumentoResponseDto> findAll() {
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findActiveTipoDocumentos();
        return tipoDocumentoMapper.toResponseDtoList(tipoDocumentoList);
    }

    @Override
    public TipoDocumentoResponseDto findById(Integer id) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoDocumento" , "id" , id.toString())
        );
        return tipoDocumentoMapper.toResponseDto(tipoDocumento);
    }

    @Override
    public TipoDocumentoResponseDto create(TipoDocumentoCreateDto tipoDocumentoCreateDto) {
        TipoDocumento tipoDocumento = tipoDocumentoMapper.toEntity(tipoDocumentoCreateDto);
        tipoDocumento.setEstado(true);
        return tipoDocumentoMapper.toResponseDto(tipoDocumentoRepository.save(tipoDocumento));
    }

    @Override
    public TipoDocumentoResponseDto update(Integer id, TipoDocumentoUpdateDto tipoDocumentoUpdateDto) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoDocumento" , "id" , id.toString())
        );
        tipoDocumento.setCodigo(tipoDocumentoUpdateDto.getCodigo());
        tipoDocumento.setNombre(tipoDocumentoUpdateDto.getNombre());
        tipoDocumento.setDescripcion(tipoDocumentoUpdateDto.getDescripcion());
        return tipoDocumentoMapper.toResponseDto(tipoDocumentoRepository.saveAndFlush(tipoDocumento));
    }

    @Override
    public TipoDocumentoResponseDto changeEstado(Integer id, boolean status) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("TipoDocumento" , "id" , id.toString())
        );
        tipoDocumento.setEstado(status);
        return tipoDocumentoMapper.toResponseDto(tipoDocumentoRepository.saveAndFlush(tipoDocumento));
    }

}
