package org.cplcursos.jdbcspringdataejercicioclase.entidades;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("empleado") // Mapea a la tabla 'empleado'
public class Empleado {
    @Id // Clave primaria
    private Integer codigoEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String extension;
    private String puesto;
    @Column("codigo_oficina")
    private String codigoOficina; // Clave foránea para Oficina
    private Integer codigoJefe; // Podría ser clave foránea a Empleado (auto-referencia)

    public String nombreYApellido() {
        return nombre + " " + apellido1;
    }
}
