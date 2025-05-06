package org.cplcursos.jdbcspringdataejercicioclase.servicios;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Cliente;
import org.cplcursos.jdbcspringdataejercicioclase.repositorios.ClienteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteSrvc {

    private final ClienteRepo clienteRepo;

    public ClienteSrvc(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public Iterable<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepo.findById(id);
    }
}
