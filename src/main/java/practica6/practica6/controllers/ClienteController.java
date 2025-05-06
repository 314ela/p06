package practica6.practica6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import practica6.practica6.models.Cliente;
import practica6.practica6.models.Pedido;
import practica6.practica6.services.ClienteService;
import practica6.practica6.services.PedidoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes();
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedidos", pedidos);
        return "/clientes";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/agregar";
    }

    @PostMapping("/agregar")
    public String agregarCliente(@ModelAttribute Cliente cliente) {
        clienteService.agregarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/editar";
    }

    @PostMapping("/editar")
    public String actualizarCliente(@ModelAttribute Cliente cliente) {
        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
