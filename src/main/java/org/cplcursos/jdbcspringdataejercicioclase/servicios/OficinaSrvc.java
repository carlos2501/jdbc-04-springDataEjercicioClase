package org.cplcursos.jdbcspringdataejercicioclase.servicios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Oficina;
import org.cplcursos.jdbcspringdataejercicioclase.repositorios.OficinaRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OficinaSrvc {

    private final OficinaRepo oficinaRepo;

    public OficinaSrvc(OficinaRepo oficinaRepo) {
        this.oficinaRepo = oficinaRepo;
    }

    public Iterable<Oficina> findAll() {
        return oficinaRepo.findAll();
    }

    public Optional<Oficina> findById(String id) {
        return  oficinaRepo.findById(id);
    }
}
