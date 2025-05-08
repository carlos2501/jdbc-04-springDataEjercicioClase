package org.cplcursos.jdbcspringdataejercicioclase.repositorios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepo extends CrudRepository<Empleado, Integer> {
    // Spring Data JDBC genera los métodos CRUD básicos.
    // Si necesitamos alguna otra consulta (método) relativo a Cliente, lo pondremos aquí
    List<Empleado> findAllByCodigoOficina(String codOfi);
}
