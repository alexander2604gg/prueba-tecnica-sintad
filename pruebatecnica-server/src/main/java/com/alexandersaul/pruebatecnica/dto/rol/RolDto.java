package com.alexandersaul.pruebatecnica.dto.rol;

import com.alexandersaul.pruebatecnica.dto.permiso.PermisoDto;
import lombok.Data;

import java.util.List;

@Data
public class RolDto {
    private Integer id;
    private String nombre;
    private List<PermisoDto> permisos;
}
