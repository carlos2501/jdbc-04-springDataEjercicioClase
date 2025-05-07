package org.cplcursos.jdbcspringdataejercicioclase.servicios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Empleado;
import org.cplcursos.jdbcspringdataejercicioclase.repositorios.EmpleadoRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoSrvc {

    private final EmpleadoRepo empleadoRepo;

    public EmpleadoSrvc(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    public Iterable<Empleado> listarEmpleados() {
        return empleadoRepo.findAll();
    }

    public Optional<Empleado> buscarEmpleadoPorId(Integer id) {
        return empleadoRepo.findById(id);
    }
}

