package com.krakedev.jdbc.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.jdbc.Conexion;

public class ClienteJdbc {
	private static final Logger log = LogManager.getLogger(ClienteJdbc.class);

	// Metodo para insertar un cliente en la base de datos
	public static Cliente insertar(String cedula, String nombre, String apellido, int edad) {
		Connection con = null;
		PreparedStatement ps = null;
		Cliente cliente = null;

		try {
			con = Conexion.getConnection();

			String sql = "INSERT INTO clientes (cedula, nombre, apellido, edad) VALUES (?, ?, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, cedula);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setInt(4, edad);

			cliente = new Cliente(cedula, nombre, apellido, edad);

			int filas = ps.executeUpdate();

			log.info("Filas insertadas: " + filas);

		} catch (Exception e) {
			log.error("Error al insertar: " + e.getMessage());
			throw new RuntimeException("Error al insertar el cliente: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return cliente;
	}

	public static List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		Connection con = null;

		try {
			con = Conexion.getConnection();
			String sql = "SELECT * FROM clientes";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getInt("edad"));

				clientes.add(c);

			}
		} catch (Exception e) {
			log.error("Error al listar los datos: " + e.getMessage());
			throw new RuntimeException("Error al listar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return clientes;
	}

	public static Cliente buscar(String cedula) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM clientes WHERE cedula=?";
		ResultSet rs = null;
		Cliente cliente = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, cedula);
			rs = ps.executeQuery();

			if (rs.next()) {
				cliente = new Cliente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getInt("edad"));
			}
		} catch (Exception e) {
			log.error("Error al buscar el cliente: " + e.getMessage());
			throw new RuntimeException("Error al buscar el cliente: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return cliente;
	}

	public static Cliente actualizar(String cedula, String nuevoNombre, String nuevoApellido,
			int nuevaEdad) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE clientes SET nombre=?, apellido=?, edad=? WHERE cedula=?";
		Cliente cliente = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nuevoNombre);
			ps.setString(2, nuevoApellido);
			ps.setInt(3, nuevaEdad);
			ps.setString(4, cedula);

			int filas = ps.executeUpdate();
			log.info("Filas actualizadas: " + filas);

			cliente = new Cliente(cedula, nuevoNombre, nuevoApellido, nuevaEdad);

		} catch (Exception e) {
			log.error("Error al actualizar el cliente: " + e.getMessage());
			throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return cliente;
	}

	public static boolean eliminar(String cedula) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM clientes WHERE cedula=?";
		boolean eliminado = false;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, cedula);
			int filas = ps.executeUpdate();
			log.info("Filas eliminadas: " + filas);
			eliminado = true;

		} catch (Exception e) {
			log.error("Error al eliminar el cliente: " + e.getMessage());
			throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return eliminado;
	}
}
