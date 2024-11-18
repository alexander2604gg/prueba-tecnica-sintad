package com.alexandersaul.pruebatecnica.controller;

import com.alexandersaul.pruebatecnica.assembler.TipoDocumentoModelAssembler;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoUpdateDto;
import com.alexandersaul.pruebatecnica.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipo-documentos")
@Validated
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private TipoDocumentoModelAssembler tipoDocumentoModelAssembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<TipoDocumentoResponseDto>>> findAll (){
        List<TipoDocumentoResponseDto> tipoDocumentoResponseDtoList = tipoDocumentoService.findAll();
        List<EntityModel<TipoDocumentoResponseDto>> entityModelList = tipoDocumentoResponseDtoList.stream()
                .map(tipoDocumentoResponseDto -> tipoDocumentoModelAssembler.toModel(tipoDocumentoResponseDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(entityModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TipoDocumentoResponseDto>> findById (@PathVariable @Positive(message = "El ID debe ser un número positivo.")  Integer id) {
        TipoDocumentoResponseDto tipoDocumentoResponseDto = tipoDocumentoService.findById(id);
        EntityModel<TipoDocumentoResponseDto> model = tipoDocumentoModelAssembler.toModel(tipoDocumentoResponseDto);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TipoDocumentoResponseDto>> create (@Valid @RequestBody TipoDocumentoCreateDto tipoDocumentoCreateDto) {
        TipoDocumentoResponseDto tipoDocumentoResponseDto = tipoDocumentoService.create(tipoDocumentoCreateDto);
        EntityModel<TipoDocumentoResponseDto> model = tipoDocumentoModelAssembler.toModel(tipoDocumentoResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<TipoDocumentoResponseDto>> update (@PathVariable @Positive(message = "El ID debe ser un número positivo.")  Integer id , @Valid @RequestBody TipoDocumentoUpdateDto tipoDocumentoUpdateDto) {
        TipoDocumentoResponseDto tipoDocumentoResponseDto = tipoDocumentoService.update( id , tipoDocumentoUpdateDto);
        EntityModel<TipoDocumentoResponseDto> model = tipoDocumentoModelAssembler.toModel(tipoDocumentoResponseDto);
        return ResponseEntity.ok(model);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EntityModel<TipoDocumentoResponseDto>> changeEstado (@PathVariable @Positive(message = "El ID debe ser un número positivo.")  Integer id , @RequestParam boolean status){
        TipoDocumentoResponseDto tipoDocumentoResponseDto = tipoDocumentoService.changeEstado(id, status);
        EntityModel<TipoDocumentoResponseDto> model = tipoDocumentoModelAssembler.toModel(tipoDocumentoResponseDto);
        return ResponseEntity.ok(model);
    }


}
