package com.alexandersaul.pruebatecnica.dto.tipoDocumento;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TipoDocumentoCreateDto {

    @NotBlank(message = "El código no puede estar vacío.")
    @Size(max = 20, message = "El código no puede exceder los 20 caracteres.")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
    private String nombre;

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres.")
    private String descripcion;

}
