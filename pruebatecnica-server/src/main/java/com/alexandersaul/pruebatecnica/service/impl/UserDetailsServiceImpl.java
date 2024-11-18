package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.dto.autenticacion.AuthLoginRequestDto;
import com.alexandersaul.pruebatecnica.dto.autenticacion.AuthResponseDto;
import com.alexandersaul.pruebatecnica.entity.Usuario;
import com.alexandersaul.pruebatecnica.repository.UsuarioRepository;
import com.alexandersaul.pruebatecnica.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByNombreUsuario((userName))
                .orElseThrow(() -> new UsernameNotFoundException(userName + " was not found"));

        Set<GrantedAuthority> authorityList = new HashSet<>();

        usuario.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNombre()))));

        usuario.getRoles().stream()
                .flatMap(role -> role.getPermisos().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getNombre())));

        return new User(usuario.getNombreUsuario() , usuario.getContrasena() , usuario.isHabilitado() , usuario.isCuentaNoExpirada(),
                usuario.isCredencialNoExpirada() , usuario.isCuentaNoBloqueada() , authorityList);
    }

    public AuthResponseDto loginUser (AuthLoginRequestDto authLoginRequestDTO) {
        String username = authLoginRequestDTO.getNombreUsuario();
        String password = authLoginRequestDTO.getContrasena();

        Authentication authentication = this.authenticate(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponseDto(username,"Login successfull" , accessToken,true);
    }

    public Authentication  authenticate  (String username , String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("invalid username or password");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());

    }

}
