package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.dto.entidad.EntidadCreateDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import com.alexandersaul.pruebatecnica.entity.Entidad;
import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.mapper.EntidadMapper;
import com.alexandersaul.pruebatecnica.repository.EntidadRepository;
import com.alexandersaul.pruebatecnica.service.impl.EntidadServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntidadServiceImplTest {

    @Mock
    private EntidadRepository entidadRepository;

    @Mock
    private EntidadValidatorService entidadValidatorService;

    @Mock
    private EntidadMapper entidadMapper;

    @InjectMocks
    private EntidadServiceImpl entidadService;

    @Test
    void testFindAllWithPagination() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<Entidad> entidadList = List.of(new Entidad(), new Entidad());
        Page<Entidad> entidadPage = new PageImpl<>(entidadList, pageable, entidadList.size());
        List<EntidadResponseDto> responseDtos = List.of(new EntidadResponseDto(), new EntidadResponseDto());


        when(entidadRepository.findActiveEntidades(pageable)).thenReturn(entidadPage);
        when(entidadMapper.toResponseDtoList(entidadList)).thenReturn(responseDtos);

        Page<EntidadResponseDto> result = entidadService.findAllWithPagination(page, size);

        assertNotNull(result);
        assertEquals(responseDtos.size(), result.getContent().size());
        verify(entidadRepository).findActiveEntidades(pageable);
        verify(entidadMapper).toResponseDtoList(entidadList);
    }

    @Test
    void testFindById() {
        int id = 1;
        Entidad entidad = new Entidad();
        entidad.setIdEntidad(id);
        EntidadResponseDto responseDto = new EntidadResponseDto();

        when(entidadRepository.findById(id)).thenReturn(Optional.of(entidad));
        when(entidadMapper.toResponseDto(entidad)).thenReturn(responseDto);

        EntidadResponseDto result = entidadService.findById(id);

        assertNotNull(result);
        verify(entidadRepository).findById(id);
        verify(entidadMapper).toResponseDto(entidad);
    }

    @Test
    void testFindByIdThrowsException() {

        int id = 1;

        when(entidadRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> entidadService.findById(id)
        );

        assertEquals("Entidad not found with the given input data id : 1", exception.getMessage());
        verify(entidadRepository).findById(id);
    }

    @Test
    void testCreate() {

        EntidadCreateDto createDto = new EntidadCreateDto();
        createDto.setIdTipoDocumento(1);
        createDto.setIdTipoContribuyente(2);

        TipoDocumento tipoDocumento = new TipoDocumento();
        TipoContribuyente tipoContribuyente = new TipoContribuyente();
        Entidad entidad = new Entidad();
        EntidadResponseDto responseDto = new EntidadResponseDto();

        when(entidadValidatorService.validateTipoDocumento(createDto.getIdTipoDocumento())).thenReturn(tipoDocumento);
        when(entidadValidatorService.validateTipoContribuyente(createDto.getIdTipoContribuyente())).thenReturn(tipoContribuyente);
        when(entidadMapper.toEntity(createDto)).thenReturn(entidad);
        when(entidadRepository.save(entidad)).thenReturn(entidad);
        when(entidadMapper.toResponseDto(entidad)).thenReturn(responseDto);

        EntidadResponseDto result = entidadService.create(createDto);

        assertNotNull(result);
        verify(entidadValidatorService).validateTipoDocumento(createDto.getIdTipoDocumento());
        verify(entidadValidatorService).validateTipoContribuyente(createDto.getIdTipoContribuyente());
        verify(entidadMapper).toEntity(createDto);
        verify(entidadRepository).save(entidad);
        verify(entidadMapper).toResponseDto(entidad);
    }

}
