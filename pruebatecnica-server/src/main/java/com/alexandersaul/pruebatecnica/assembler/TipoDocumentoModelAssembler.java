package com.alexandersaul.pruebatecnica.assembler;

import com.alexandersaul.pruebatecnica.controller.TipoDocumentoController;
import com.alexandersaul.pruebatecnica.dto.tipoDocumento.TipoDocumentoResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TipoDocumentoModelAssembler {

    public EntityModel<TipoDocumentoResponseDto> toModel(TipoDocumentoResponseDto tipoDocumentoResponseDto) {
        return EntityModel.of(tipoDocumentoResponseDto,

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .findById(tipoDocumentoResponseDto.getId())).withSelfRel(),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .update(tipoDocumentoResponseDto.getId(), null)).withRel("update").withType("PATCH"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                                .changeEstado(tipoDocumentoResponseDto.getId(), !tipoDocumentoResponseDto.getEstado()))
                        .withRel("toggle-estado").withType("PATCH"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TipoDocumentoController.class)
                        .findAll()).withRel("list").withType("GET")
        );
    }

}
