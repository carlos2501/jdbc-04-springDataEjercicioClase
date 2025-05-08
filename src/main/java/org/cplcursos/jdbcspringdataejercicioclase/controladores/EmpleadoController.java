package org.cplcursos.jdbcspringdataejercicioclase.controladores;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Empleado;
import org.cplcursos.jdbcspringdataejercicioclase.entidades.Oficina;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.EmpleadoSrvc;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.OficinaSrvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class EmpleadoController {

    private final EmpleadoSrvc empleadoSrvc;
    private final OficinaSrvc oficinaSrvc;

    public EmpleadoController(EmpleadoSrvc empleadoSrvc, OficinaSrvc oficinaSrvc) {
        this.empleadoSrvc = empleadoSrvc;
        this.oficinaSrvc = oficinaSrvc;
    }

    @GetMapping("/empleados/{id}")
    public String mostrarFichaEmpleado(@PathVariable Integer id, Model model) {
        Optional<Empleado> empleadoOpt = empleadoSrvc.buscarEmpleadoPorId(id);
        if (empleadoOpt.isEmpty()) {
            return "error"; // O página de error personalizada
        }
        Empleado empleado = empleadoOpt.get();

        // Obtener oficina del empleado
        Oficina oficinaEmpleado = null;
        for (Oficina oficina : oficinaSrvc.findAll()) {
            if (oficina.getCodigoOficina().equals(empleado.getCodigoOficina())) {
                oficinaEmpleado = oficina;
                break;
            }
        }

        // Obtener lista de oficinas para el select
        List<Oficina> oficinas = (List<Oficina>) oficinaSrvc.findAll();

        // Obtener empleados de la oficina para el select jefe
        List<Empleado> empleadosOficina = ((List<Empleado>) empleadoSrvc.buscarEmpleadoPorOficina(empleado.getCodigoOficina()));

        // Obtener jefe del empleado
        Empleado jefe = null;
        if (empleado.getCodigoJefe() != null) {
            Optional<Empleado> jefeOpt = empleadoSrvc.buscarEmpleadoPorId(Integer.valueOf(empleado.getCodigoJefe()));
            if (jefeOpt.isPresent()) {
                jefe = jefeOpt.get();
            }
        }

        model.addAttribute("empleado", empleado);
        model.addAttribute("oficinaEmpleado", oficinaEmpleado);
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("empleadosOficina", empleadosOficina);
        model.addAttribute("jefe", jefe);

        return "fichaEmpleado";
    }

    /*@PostMapping("/empleados/{id}/actualizar")
    public String actualizarEmpleado(@PathVariable Integer id,
                                     @RequestParam String codigoOficina,
                                     @RequestParam(required = false) Integer codigoJefe) {
        Optional<Empleado> empleadoOpt = empleadoSrvc.buscarEmpleadoPorId(id);
        if (empleadoOpt.isEmpty()) {
            return "error";
        }
        Empleado empleado = empleadoOpt.get();
        empleado.setCodigoOficina(codigoOficina);
        if (codigoJefe != null) {
            empleado.setCodigoJefe(String.valueOf(codigoJefe));
        } else {
            empleado.setCodigoJefe(null);
        }
        empleadoSrvc.guardarEmpleado(empleado);
        return "redirect:/empleados/" + id;
    }*/
}

