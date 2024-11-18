package com.alexandersaul.pruebatecnica.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_usuario" , schema = "public")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nombreUsuario;
    private String contrasena;
    private boolean habilitado;
    private boolean cuentaNoExpirada;
    private boolean cuentaNoBloqueada;
    private boolean credencialNoExpirada;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable (name = "usuarios_roles" , joinColumns = @JoinColumn(name = "usuario_id") ,
            inverseJoinColumns = @JoinColumn(name="rol_id"))
    private List<Rol> roles;
}
