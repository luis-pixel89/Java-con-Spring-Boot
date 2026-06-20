package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertarVehiculo {
	private static final Logger log = LogManager.getLogger(InsertarVehiculo.class);

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();

			String sql = "insert into vehiculos (placa, marca, modelo, anio, precio, color, disponible) values (?, ?, ?, ?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, "PQD-997");
			ps.setString(2, "Kia");
			ps.setString(3, "Picanto");
			ps.setInt(4, 2006);
			ps.setDouble(5, 5000.00);
			ps.setString(6, "Rojo");
			ps.setBoolean(7, true);

			int filas = ps.executeUpdate();

			log.info("Filas insertadas: " + filas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error al insertar un vehiculo: " + e.getMessage());
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
