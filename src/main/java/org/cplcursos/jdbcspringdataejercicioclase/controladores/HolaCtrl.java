package org.cplcursos.jdbcspringdataejercicioclase.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaCtrl {

    @GetMapping("/saludo") // Mapea las peticiones GET a /saludo a este método
    public String mostrarPaginaHola(Model model) {
        // Agrega el título al modelo para que esté disponible en la plantilla Thymeleaf
        model.addAttribute("titulo", "Hola a todo el mundo");
        // Devuelve el nombre de la plantilla HTML (sin la extensión .html)
        return "paginaHola"; // Esto buscará paginaHola.html en src/main/resources/templates
    }
}
