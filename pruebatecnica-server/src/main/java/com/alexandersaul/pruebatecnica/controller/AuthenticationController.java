package com.alexandersaul.pruebatecnica.controller;

import com.alexandersaul.pruebatecnica.dto.autenticacion.AuthLoginRequestDto;
import com.alexandersaul.pruebatecnica.dto.autenticacion.AuthResponseDto;
import com.alexandersaul.pruebatecnica.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody @Valid AuthLoginRequestDto userRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userDetailsService.loginUser(userRequest));
    }

}
