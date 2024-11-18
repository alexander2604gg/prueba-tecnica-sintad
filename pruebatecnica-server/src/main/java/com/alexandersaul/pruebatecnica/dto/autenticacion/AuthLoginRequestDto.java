package com.alexandersaul.pruebatecnica.dto.autenticacion;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthLoginRequestDto {
    @NotBlank(message = "El nombre de usuario no debe estar vacio")
    private String nombreUsuario;
    @NotBlank(message = "La contrasena no debe estar vacia")
    private String contrasena;
}
