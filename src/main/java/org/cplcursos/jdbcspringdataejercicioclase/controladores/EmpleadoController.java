package org.cplcursos.jdbcspringdataejercicioclase.controladores;

import org.cplcursos.jdbcspringdataejercicioclase.entidades.Empleado;
import org.cplcursos.jdbcspringdataejercicioclase.entidades.Oficina;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.EmpleadoSrvc;
import org.cplcursos.jdbcspringdataejercicioclase.servicios.OficinaSrvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            Optional<Empleado> jefeOpt = empleadoSrvc.buscarEmpleadoPorId(empleado.getCodigoJefe());
            if (jefeOpt.isPresent()) {
                jefe = jefeOpt.get();
            }
        }

        // Si el jefe no pertenece a la oficina del empleado...
        if(jefe.getCodigoOficina() != empleado.getCodigoOficina()) {
            // debo añadir su nombre a la lista de empleados para que aparezca como seleccionado
            empleadosOficina.add(jefe);
        }

        model.addAttribute("empleado", empleado);
        model.addAttribute("oficinaEmpleado", oficinaEmpleado);
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("empleadosOficina", empleadosOficina);
        model.addAttribute("jefe", jefe);

        return "fichaEmpleado";
    }

    @PostMapping("/empleados/{id}/actualizar")
    public String actualizarEmpleado(@PathVariable Integer id,
                                     @RequestParam String codigoOficina,
                                     @RequestParam(required = false) Integer codigoJefe) {
        // Obtenemos el empleado
        Optional<Empleado> empleadoOpt = empleadoSrvc.buscarEmpleadoPorId(id);
        if (empleadoOpt.isEmpty()) {
            return "error";
        }
        Empleado empleado = empleadoOpt.get();
        // Actualizamos con el nuevo código de oficina. Si no ha cambiado, se dejará el que tenía
        empleado.setCodigoOficina(codigoOficina);
        // Actualizamos el código del jefe. Si no ha cambiado se deja el que tenía.
        if (codigoJefe != null) {
            empleado.setCodigoJefe(codigoJefe);
        } else {
            empleado.setCodigoJefe(null);
        }
        // se guarda en la BBDD
        empleadoSrvc.guardarEmpleado(empleado);
        // Se recarga la ficha del empleado
        return "redirect:/empleados/" + id;
    }
}

