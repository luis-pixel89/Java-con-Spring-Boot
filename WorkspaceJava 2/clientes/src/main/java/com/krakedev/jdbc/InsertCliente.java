package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertCliente {
	private static final Logger log = LogManager.getLogger(InsertCliente.class);
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres2";
	private static final String USER = "postgres";
	private static final String PASSWORD = "4189";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;

		String sql = "INSERT INTO clientes (cedula, nombre, apellido, edad) VALUES (?,?,?,?)";

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			ps = con.prepareStatement(sql);

			ps.setString(1, "1111111111");
			ps.setString(2, "Marcos");
			ps.setString(3, "Sanchez");
			ps.setInt(4, 19);

			int filas = ps.executeUpdate();

			log.info("Conexion exitosa");
			log.info("Filas insertadas: " + filas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error al conectar a la base de datos: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}

	}

}
