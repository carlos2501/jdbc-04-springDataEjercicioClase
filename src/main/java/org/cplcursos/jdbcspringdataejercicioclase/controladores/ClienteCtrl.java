package org.cplcursos.jdbcspringdataejercicioclase.controladores;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Cliente;
import org.cplcursos.jdbcspringdataejercicioclase.entidades.Empleado;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.ClienteSrvc;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.EmpleadoSrvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteCtrl {

    private final ClienteSrvc clienteSrvc;
    private final EmpleadoSrvc empleadoSrvc;

    public ClienteCtrl(ClienteSrvc clienteSrvc, EmpleadoSrvc empleadoSrvc) {
        this.clienteSrvc = clienteSrvc;
        this.empleadoSrvc = empleadoSrvc;
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
        Cliente cli = clienteSrvc.buscarClientePorId(id).orElse(new Cliente());
        modelo.addAttribute("cliente",cli );
        Empleado empleado = empleadoSrvc.buscarEmpleadoPorId(cli.getCodigo_rep_ventas()).orElse(new Empleado());
        String representante = empleado.getNombre() + " " + empleado.getApellido1();
        modelo.addAttribute("representante",representante);
        modelo.addAttribute("listaRepresentantes",empleadoSrvc.listarEmpleados());
        return "fichaCliente";
    }
}
