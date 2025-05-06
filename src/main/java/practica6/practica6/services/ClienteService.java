package practica6.practica6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import practica6.practica6.models.Cliente;

@Service
public class ClienteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cliente> obtenerClientes() {
        String sql = "SELECT id_cliente, nombre_cliente, correo, telefono FROM clientes";
        return jdbcTemplate.query(sql, clienteRowMapper());
    }

    public Cliente obtenerClientePorId(Long id_cliente) {
        String sql = "SELECT id_cliente, nombre_cliente, correo, telefono FROM clientes WHERE id_cliente = ?";
        return jdbcTemplate.queryForObject(sql, clienteRowMapper(), id_cliente);
    }

    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (id_cliente, nombre_cliente, correo, telefono) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getId_cliente(), cliente.getNombre_cliente(), cliente.getCorreo(), cliente.getTelefono());
    }

    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre_cliente = ?, correo = ?, telefono = ? WHERE id_cliente = ?";
        jdbcTemplate.update(sql, cliente.getNombre_cliente(), cliente.getCorreo(), cliente.getTelefono(), cliente.getId_cliente());
    }

    public void eliminarCliente(Long id_cliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        jdbcTemplate.update(sql, id_cliente);
    }

    private RowMapper<Cliente> clienteRowMapper() {
        return (rs, rowNum) -> {
            Cliente cliente = new Cliente();
            cliente.setId_cliente(rs.getLong("id_cliente"));
            cliente.setNombre_cliente(rs.getString("nombre_cliente"));
            cliente.setCorreo(rs.getString("correo"));
            cliente.setTelefono(rs.getString("telefono"));
            return cliente;
        };
    }
}
