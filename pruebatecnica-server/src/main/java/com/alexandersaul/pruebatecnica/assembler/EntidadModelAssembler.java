package com.alexandersaul.pruebatecnica.assembler;

import com.alexandersaul.pruebatecnica.controller.EntidadController;
import com.alexandersaul.pruebatecnica.dto.entidad.EntidadResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class EntidadModelAssembler {

    public EntityModel<EntidadResponseDto> toModel(EntidadResponseDto entidadResponseDto) {
        return EntityModel.of(entidadResponseDto,

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EntidadController.class)
                        .findById(entidadResponseDto.getId())).withSelfRel(),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EntidadController.class)
                        .update(entidadResponseDto.getId(), null)).withRel("update").withType("PATCH"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EntidadController.class)
                                .changeEstado(entidadResponseDto.getId(), !entidadResponseDto.getEstado()))
                        .withRel("toggle-estado").withType("PATCH"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EntidadController.class)
                        .findAllWithPagination(0, 5)).withRel("list").withType("GET")
        );
    }

}
