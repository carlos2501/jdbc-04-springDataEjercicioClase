package org.cplcursos.jdbcspringdataejercicioclase.repositorios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Oficina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepo extends CrudRepository<Oficina, String> {
}
