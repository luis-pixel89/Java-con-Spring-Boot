package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateVehiculo {
	private static final Logger log = LogManager.getLogger(UpdateVehiculo.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = "UPDATE vehiculos SET marca=?, modelo=?, anio=?, precio=?, color=?, disponible=?, kilometraje=? WHERE placa=?";
			
			ps=con.prepareStatement(sql);

			ps.setString(1, "Toyota");
			ps.setString(2, "Corolla");
			ps.setInt(3, 2020);
			ps.setDouble(4, 20000.00);
			ps.setString(5, "Rojo");
			ps.setBoolean(6, true);
			ps.setInt(7, 50000);
			ps.setString(8, "PQD-997");

			int filas = ps.executeUpdate();

			log.info("Filas actualizadas: " + filas);

		} catch (Exception e) {
			log.error("Error al actualizar datos: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}

	}
}
