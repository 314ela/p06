package practica6.practica6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import practica6.practica6.models.Pedido;

@Service
public class PedidoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener todos los pedidos
    public List<Pedido> obtenerPedidos() {
        String sql = "SELECT id_pedido, fecha, total, estado, id_cliente FROM pedidos";
        return jdbcTemplate.query(sql, pedidoRowMapper());
    }

    // Obtener un solo pedido por su ID
    public Pedido obtenerPedidoPorId(Long id_pedido) {
        String sql = "SELECT id_pedido, fecha, total, estado, id_cliente FROM pedidos WHERE id_pedido = ?";
        return jdbcTemplate.queryForObject(sql, pedidoRowMapper(), id_pedido);
    }

    // Agregar un nuevo pedido
    public void agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_pedido, fecha, total, estado, id_cliente) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pedido.getId_pedido(), pedido.getFecha(), pedido.getTotal(), pedido.getEstado(), pedido.getId_cliente());
    }

    // Actualizar un pedido existente
    public void actualizarPedido(Pedido pedido) {
        String sql = "UPDATE pedidos SET fecha = ?, total = ?, estado = ?, id_cliente = ? WHERE id_pedido = ?";
        jdbcTemplate.update(sql, pedido.getFecha(), pedido.getTotal(), pedido.getEstado(), pedido.getId_cliente(), pedido.getId_pedido());
    }

    // Eliminar un pedido
    public void eliminarPedido(Long id_pedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        jdbcTemplate.update(sql, id_pedido);
    }

    // Mapeo de la fila para el Pedido
    private RowMapper<Pedido> pedidoRowMapper() {
        return (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setId_pedido(rs.getLong("id_pedido"));
            pedido.setFecha(rs.getDate("fecha"));
            pedido.setTotal(rs.getBigDecimal("total"));
            pedido.setEstado(rs.getString("estado"));
            pedido.setId_cliente(rs.getLong("id_cliente"));
            return pedido;
        };
    }
}
