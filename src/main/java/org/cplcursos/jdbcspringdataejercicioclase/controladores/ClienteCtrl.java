package org.cplcursos.jdbcspringdataejercicioclase.controladores;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Cliente;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.ClienteSrvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteCtrl {

    private final ClienteSrvc clienteSrvc;

    public ClienteCtrl(ClienteSrvc clienteSrvc) {
        this.clienteSrvc = clienteSrvc;
    }

    @GetMapping("/clientes")
    public String listaClientes(Model modelo){
        modelo.addAttribute("titulo", "Lista de clientes");
        modelo.addAttribute("clientes", clienteSrvc.listarClientes());
        return "listaClientes";
    }

    @GetMapping("/clientes/{id}")
    public String editarCliente(@PathVariable Integer id, Model modelo){
        modelo.addAttribute("titulo", "Editar cliente " + id);
        modelo.addAttribute("cliente", clienteSrvc.buscarClientePorId(id).orElse(new Cliente()));
        return "fichaCliente";
    }
}
