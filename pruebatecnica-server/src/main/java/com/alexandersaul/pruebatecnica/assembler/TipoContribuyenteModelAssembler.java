package com.alexandersaul.pruebatecnica.assembler;

import com.alexandersaul.pruebatecnica.controller.TipoDocumentoController;
import com.alexandersaul.pruebatecnica.dto.tipoContribuyente.TipoContribuyenteResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TipoContribuyenteModelAssembler {

    public EntityModel<TipoContribuyenteResponseDto> toModel(TipoContribuyenteResponseDto tipoContribuyenteResponseDto) {
        return EntityModel.of(tipoContribuyenteResponseDto,

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .findById(tipoContribuyenteResponseDto.getId())).withSelfRel(),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .update(tipoContribuyenteResponseDto.getId(), null)).withRel("update").withType("PATCH"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                                .changeEstado(tipoContribuyenteResponseDto.getId(), !tipoContribuyenteResponseDto.getEstado()))
                        .withRel("toggle-estado").withType("PATCH"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .findAll()).withRel("list").withType("GET")
        );
    }

}
