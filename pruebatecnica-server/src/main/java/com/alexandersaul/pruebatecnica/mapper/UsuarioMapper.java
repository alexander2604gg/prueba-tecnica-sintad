package com.alexandersaul.pruebatecnica.mapper;

import com.alexandersaul.pruebatecnica.dto.rol.RolDto;
import com.alexandersaul.pruebatecnica.dto.usuario.UsuarioDto;
import com.alexandersaul.pruebatecnica.entity.Rol;
import com.alexandersaul.pruebatecnica.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto (Usuario usuario);
    @Mapping(target = "cuentaNoExpirada", ignore = true)
    @Mapping(target = "cuentaNoBloqueada", ignore = true)
    @Mapping(target = "credencialNoExpirada", ignore = true)
    Usuario toEntity (UsuarioDto usuarioDto);
    List<UsuarioDto> toDtoList (List<Usuario> usuarios);
    List<Usuario> toEntityList(List<UsuarioDto> usuarioDtos);

}
