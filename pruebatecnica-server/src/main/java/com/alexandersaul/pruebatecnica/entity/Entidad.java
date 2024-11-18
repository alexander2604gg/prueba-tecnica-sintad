package com.alexandersaul.pruebatecnica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_entidad", schema = "public" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "nro_documento")
})
public class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_documento", nullable = false, foreignKey = @ForeignKey(name = "tb_entidad_ibfk_3"))
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento", nullable = false, length = 25)
    private String nroDocumento;

    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 100)
    private String nombreComercial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_contribuyente", foreignKey = @ForeignKey(name = "tb_entidad_ibfk_2"))
    private TipoContribuyente tipoContribuyente;

    @Column(name = "direccion", length = 250)
    private String direccion;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
