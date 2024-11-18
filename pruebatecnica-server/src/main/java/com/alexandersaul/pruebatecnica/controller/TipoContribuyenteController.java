package com.alexandersaul.pruebatecnica.controller;

import com.alexandersaul.pruebatecnica.assembler.TipoContribuyenteModelAssembler;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteCreateDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteResponseDto;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteUpdateDto;
import com.alexandersaul.pruebatecnica.service.TipoContribuyenteService;
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
@RequestMapping("/tipo-contribuyentes")
@Validated
public class TipoContribuyenteController {

    @Autowired
    private TipoContribuyenteService tipoContribuyenteService;
    @Autowired
    private TipoContribuyenteModelAssembler tipoContribuyenteModelAssembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<TipoContribuyenteResponseDto>>> findAll (){
         List<TipoContribuyenteResponseDto> tipoContribuyenteResponseDtoList = tipoContribuyenteService.findAll();
         List<EntityModel<TipoContribuyenteResponseDto>> entityModelList = tipoContribuyenteResponseDtoList.stream()
                 .map(tipoContribuyenteResponseDto -> tipoContribuyenteModelAssembler.toModel(tipoContribuyenteResponseDto))
                 .collect(Collectors.toList());
         return ResponseEntity.ok(entityModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TipoContribuyenteResponseDto>> findById (@PathVariable @Positive(message = "El ID debe ser un número positivo.")  Integer id) {
        TipoContribuyenteResponseDto tipoContribuyenteResponseDto = tipoContribuyenteService.findById(id);
        EntityModel<TipoContribuyenteResponseDto> model = tipoContribuyenteModelAssembler.toModel(tipoContribuyenteResponseDto);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TipoContribuyenteResponseDto>> create (@Valid @RequestBody TipoContribuyenteCreateDto tipoContribuyenteCreateDto) {
        TipoContribuyenteResponseDto tipoContribuyenteResponseDto = tipoContribuyenteService.create(tipoContribuyenteCreateDto);
        EntityModel<TipoContribuyenteResponseDto> model = tipoContribuyenteModelAssembler.toModel(tipoContribuyenteResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<TipoContribuyenteResponseDto>> update (@PathVariable @Positive(message = "El ID debe ser un número positivo.") Integer id , @Valid @RequestBody TipoContribuyenteUpdateDto tipoContribuyenteUpdateDto) {
        TipoContribuyenteResponseDto tipoContribuyenteResponseDto = tipoContribuyenteService.update( id , tipoContribuyenteUpdateDto);
        EntityModel<TipoContribuyenteResponseDto> model = tipoContribuyenteModelAssembler.toModel(tipoContribuyenteResponseDto);
        return ResponseEntity.ok(model);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EntityModel<TipoContribuyenteResponseDto>> changeEstado (@PathVariable @Positive(message = "El ID debe ser un número positivo.") Integer id , @RequestParam boolean status){
        TipoContribuyenteResponseDto tipoContribuyenteResponseDto = tipoContribuyenteService.changeEstado(id, status);
        EntityModel<TipoContribuyenteResponseDto> model = tipoContribuyenteModelAssembler.toModel(tipoContribuyenteResponseDto);
        return ResponseEntity.ok(model);
    }

}
