package com.alexandersaul.pruebatecnica.controller;

import com.alexandersaul.pruebatecnica.dto.usuario.UsuarioDto;
import com.alexandersaul.pruebatecnica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> usuarioDtoList = usuarioService.findAll();
        return ResponseEntity.ok(usuarioDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> finUsuarioById(@PathVariable Integer id) {
        UsuarioDto usuarioDto = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto savedUsuario = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUser(@PathVariable Integer id , @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto updatedUsuario = usuarioService.update(id , usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
