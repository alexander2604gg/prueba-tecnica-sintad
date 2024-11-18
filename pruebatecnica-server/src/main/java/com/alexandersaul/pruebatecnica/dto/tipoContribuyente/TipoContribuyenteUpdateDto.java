package com.alexandersaul.pruebatecnica.dto.tipoContribuyente;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TipoContribuyenteUpdateDto {
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.")
    private String nombre;
}
