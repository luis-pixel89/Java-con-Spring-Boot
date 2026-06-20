package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelectVehiculo {
	private static final Logger log = LogManager.getLogger(SelectVehiculo.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();

			String sql = "SELECT * FROM vehiculos";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String placa=rs.getString("placa");
				 String marca=rs.getString("marca");
				 String modelo=rs.getString("modelo");
				 int anio=rs.getInt("anio");
				 double precio=rs.getDouble("precio");
				 String color=rs.getString("color");
				 boolean disponible=rs.getBoolean("disponible");

				log.info("Vehiculo: Placa: " + placa + " Marca: " + marca + " Modelo: " + modelo + " Año: "
						+ anio + " Precio: " + precio + " Color: " + color + " Disponible: " + disponible);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error al traer datos: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}

	}

}
