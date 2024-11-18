package com.alexandersaul.pruebatecnica.controller;

import com.alexandersaul.pruebatecnica.assembler.EntidadModelAssembler;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadCreateDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadUpdateDto;
import com.alexandersaul.pruebatecnica.service.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/entidades")
@Validated
public class EntidadController {

    @Autowired
    private EntidadService entidadService;
    @Autowired
    private EntidadModelAssembler entidadModelAssembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<EntidadResponseDto>>> findAllWithPagination (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){

        Page<EntidadResponseDto> entidadResponseDtoPage = entidadService.findAllWithPagination(page , size);
        Page<EntityModel<EntidadResponseDto>> entityModelPage = entidadResponseDtoPage.map(entidadResponseDto ->
                entidadModelAssembler.toModel(entidadResponseDto));
        return ResponseEntity.ok(entityModelPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EntidadResponseDto>> findById (@PathVariable  @Positive(message = "El ID debe ser un número positivo.") Integer id){
        EntidadResponseDto entidadResponseDto = entidadService.findById(id);
        EntityModel<EntidadResponseDto> model = entidadModelAssembler.toModel(entidadResponseDto);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/by-nro-documento")
    public ResponseEntity<EntityModel<EntidadResponseDto>> findByNumeroDocumento (@RequestParam String nrdDocumento){
        EntidadResponseDto entidadResponseDto = entidadService.findByNumeroDocumento(nrdDocumento);
        EntityModel<EntidadResponseDto> model = entidadModelAssembler.toModel(entidadResponseDto);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<EntidadResponseDto>> save (@Valid @RequestBody EntidadCreateDto entidadCreateDto){

        EntidadResponseDto entidadResponseDto = entidadService.create(entidadCreateDto);
        EntityModel<EntidadResponseDto> entityModel = entidadModelAssembler.toModel(entidadResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<EntidadResponseDto>> update (@PathVariable @Positive(message = "El ID debe ser un número positivo.") Integer id , @Valid @RequestBody EntidadUpdateDto entidadUpdateDto){
        EntidadResponseDto entidadResponseDto = entidadService.update(id , entidadUpdateDto);
        EntityModel<EntidadResponseDto> entityModel = entidadModelAssembler.toModel(entidadResponseDto);

        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EntityModel<EntidadResponseDto>> changeEstado(@PathVariable @Positive(message = "El ID debe ser un número positivo.") Integer id, @RequestParam boolean status) {
        EntidadResponseDto entidadResponseDto = entidadService.changeEstado(id, status);
        EntityModel<EntidadResponseDto> entityModel = entidadModelAssembler.toModel(entidadResponseDto);
        return ResponseEntity.ok(entityModel);
    }


}
