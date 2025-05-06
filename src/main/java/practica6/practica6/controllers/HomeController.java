package practica6.practica6.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String home() {
        return "index";
    }
    @GetMapping("/login/Registrarse")
    public String registro() {
        return "/login/registro";
    }

    @GetMapping("/login/ContraseñaOlvidada")
    public String contraseña() {
        return "/login/contrasena";
    }
}
