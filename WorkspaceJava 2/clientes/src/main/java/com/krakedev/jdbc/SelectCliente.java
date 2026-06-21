package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelectCliente {
	private static final Logger log = LogManager.getLogger(SelectCliente.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();

			String sql = "SELECT * FROM clientes";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cedula = rs.getString("cedula");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");

				log.info("Cliente: Cedula: " + cedula + " Nombre: " + nombre + " Apellido: " + apellido + " Edad: "
						+ edad);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error al traer datos: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}

	}

}
