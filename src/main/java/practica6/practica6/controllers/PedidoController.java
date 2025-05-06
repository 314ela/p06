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

import practica6.practica6.models.Pedido;
import practica6.practica6.services.ClienteService;
import practica6.practica6.services.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("clientes", clienteService.obtenerClientes());
        return "pedidos/listar";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.obtenerClientes());
        return "/pedidos/agregar";
    }

    @PostMapping("/agregar")
    public String agregarPedido(@ModelAttribute Pedido pedido) {
        pedidoService.agregarPedido(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPedido(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.obtenerClientes());
        return "pedidos/editar";
    }

    @PostMapping("/editar")
    public String actualizarPedido(@ModelAttribute Pedido pedido) {
        pedidoService.actualizarPedido(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos";
    }
}
