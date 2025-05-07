package org.cplcursos.jdbcspringdataejercicioclase.entidades;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("empleado") // Mapea a la tabla 'empleado'
public class Empleado {
    @Id // Clave primaria
    private Integer codigo_empleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String extension;
    private String puesto;
    private String codigo_oficina; // Clave foránea para Oficina
    private String codigo_jefe; // Podría ser clave foránea a Empleado (auto-referencia)

    public String nombreYApellido() {
        return nombre + " " + apellido1;
    }
}
