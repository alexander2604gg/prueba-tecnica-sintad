package com.alexandersaul.pruebatecnica.dto.autenticacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {

    private String nombreUsuario;
    private String mensaje;
    private String jwt;
    private Boolean status;

}
