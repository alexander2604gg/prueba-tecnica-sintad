package com.alexandersaul.pruebatecnica.dto.tipoDocumento;

import lombok.Data;

@Data
public class TipoDocumentoResponseDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private Boolean estado;
}
