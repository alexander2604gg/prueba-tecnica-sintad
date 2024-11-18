package com.alexandersaul.pruebatecnica.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_tipo_contribuyente" , schema = "public")
public class TipoContribuyente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoContribuyente;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false)
    private Boolean estado;


}
