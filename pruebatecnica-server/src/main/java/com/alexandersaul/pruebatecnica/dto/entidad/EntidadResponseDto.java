package com.alexandersaul.pruebatecnica.dto.entidad;

import lombok.Data;

@Data
public class EntidadResponseDto {
    private Integer id;
    private String tipoDocumento;
    private String nroDocumento;
    private String razonSocial;
    private String nombreComercial;
    private String tipoContribuyente;
    private String direccion;
    private String telefono;
    private Boolean estado;
}
