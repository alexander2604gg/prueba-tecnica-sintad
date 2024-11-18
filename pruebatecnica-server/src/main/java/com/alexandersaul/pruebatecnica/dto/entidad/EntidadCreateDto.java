package com.alexandersaul.pruebatecnica.dto.entidad;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EntidadCreateDto {

    @NotNull(message = "El tipo de documento es obligatorio.")
    private Integer idTipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio.")
    @Size(max = 25, message = "El número de documento no puede exceder los 25 caracteres.")
    private String nroDocumento;

    @NotBlank(message = "La razón social es obligatoria.")
    @Size(max = 100, message = "La razón social no puede exceder los 100 caracteres.")
    private String razonSocial;

    @Size(max = 100, message = "El nombre comercial no puede exceder los 100 caracteres.")
    private String nombreComercial;

    private Integer idTipoContribuyente;

    @Size(max = 250, message = "La dirección no puede exceder los 250 caracteres.")
    private String direccion;

    @Size(max = 50, message = "El teléfono no puede exceder los 50 caracteres.")
    private String telefono;

}
