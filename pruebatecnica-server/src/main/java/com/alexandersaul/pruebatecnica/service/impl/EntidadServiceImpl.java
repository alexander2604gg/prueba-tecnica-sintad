package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.entidad.EntidadCreateDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadUpdateDto;
import com.alexandersaul.pruebatecnica.entity.Entidad;
import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.EntidadMapper;
import com.alexandersaul.pruebatecnica.repository.EntidadRepository;
import com.alexandersaul.pruebatecnica.service.EntidadService;
import com.alexandersaul.pruebatecnica.service.EntidadValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class EntidadServiceImpl implements EntidadService {

    @Autowired
    private EntidadRepository entidadRepository;
    @Autowired
    private EntidadValidatorService entidadValidatorService;
    @Autowired
    private EntidadMapper entidadMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<EntidadResponseDto> findAllWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page , size);
        Page<Entidad> entidadPage = entidadRepository.findActiveEntidades(pageable);
        List<EntidadResponseDto> entidadResponseDtoList = entidadMapper.toResponseDtoList(entidadPage.getContent());
        return new PageImpl<>(entidadResponseDtoList, pageable , entidadPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public EntidadResponseDto findById(Integer id) {
         Entidad entidad = entidadRepository.findById(id).orElseThrow(
                 () -> new ResourceNotFoundException("Entidad" , "id" , id.toString())
         );
         return entidadMapper.toResponseDto(entidad);
    }

    @Override
    public EntidadResponseDto findByNumeroDocumento(String nroDocumento) {
        Entidad entidad = entidadRepository.findByNroDocumento(nroDocumento).orElseThrow(
                () -> new ResourceNotFoundException("Entidad" , "nroDocumento" , nroDocumento)
        );
        return entidadMapper.toResponseDto(entidad);
    }

    @Transactional
    @Override
    public EntidadResponseDto create(EntidadCreateDto entidadCreateDto) {
        TipoDocumento tipoDocumento = entidadValidatorService.validateTipoDocumento(entidadCreateDto.getIdTipoDocumento());
        TipoContribuyente tipoContribuyente = entidadValidatorService.validateTipoContribuyente(entidadCreateDto.getIdTipoContribuyente());
        Entidad entidad = entidadMapper.toEntity(entidadCreateDto);
        entidad.setTipoDocumento(tipoDocumento);
        entidad.setTipoContribuyente(tipoContribuyente);
        entidad.setEstado(true);
        return entidadMapper.toResponseDto(entidadRepository.save(entidad));
    }

    @Transactional
    @Override
    public EntidadResponseDto update(Integer id , EntidadUpdateDto entidadUpdateDto) {
        Entidad entidad = entidadRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entidad" , "id" , id.toString())
        );
        entidad.setNombreComercial(entidadUpdateDto.getNombreComercial());
        entidad.setDireccion(entidadUpdateDto.getDireccion());
        entidad.setTelefono(entidadUpdateDto.getTelefono());

        return entidadMapper.toResponseDto(entidadRepository.saveAndFlush(entidad));

    }

    @Transactional
    @Override
    public EntidadResponseDto changeEstado(Integer id , boolean status) {
        Entidad entidad = entidadRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entidad" , "id" , id.toString())
        );
        entidad.setEstado(status);
        return entidadMapper.toResponseDto(entidadRepository.saveAndFlush(entidad));
    }


}
