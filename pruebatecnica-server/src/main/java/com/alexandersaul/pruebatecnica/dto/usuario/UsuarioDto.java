package com.alexandersaul.pruebatecnica.dto.usuario;

import com.alexandersaul.pruebatecnica.dto.rol.RolDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UsuarioDto {
    private Integer id;
    private String nombreUsuario;
    private String contrasena;
    private boolean habilitado;
    private List<RolDto> roles;
}
