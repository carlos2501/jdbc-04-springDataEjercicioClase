package org.cplcursos.jdbcspringdataejercicioclase.repositorios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends CrudRepository<Cliente, Integer> {
    // Spring Data JDBC genera los métodos CRUD básicos.
    // Si necesitamos alguna otra consulta (método) relativo a Cliente, lo pondremos aquí
}
