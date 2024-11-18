package com.alexandersaul.pruebatecnica.dto.entidad;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EntidadUpdateDto {

    @NotBlank(message = "El nombre comercial no puede estar vacío")
    @Size(max = 100, message = "El nombre comercial no puede exceder los 100 caracteres.")
    private String nombreComercial;
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 250, message = "La dirección no puede exceder los 250 caracteres.")
    private String direccion;
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 50, message = "El teléfono no puede exceder los 50 caracteres.")
    private String telefono;

}
