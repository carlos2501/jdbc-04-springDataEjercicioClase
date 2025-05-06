package org.cplcursos.jdbcspringdataejercicioclase.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("cliente")
public class Cliente {
    @Id
    private Integer codigo_cliente;
    private String nombre_cliente;
    private String nombre_contacto;
    private String apellido_contacto;
    private String telefono;
    private String fax;
    private String linea_direccion1;
    private String linea_direccion2;
    private String ciudad;
    private String region;
    private String pais;
    private String codigo_postal;
    @Column("codigo_empleado_rep_ventas")
    private Integer codigo_rep_ventas;
    private Double limite_credito;

    @Override
    public String toString(){
        return "Cliente " + nombre_cliente + " teléfono " + telefono;
    }
}
