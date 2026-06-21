package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateCliente {
	private static final Logger log = LogManager.getLogger(UpdateCliente.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = "UPDATE clientes SET nombre=?, apellido=?, edad=? WHERE cedula=?";
			
			ps=con.prepareStatement(sql);

			ps.setString(1, "Ivan");
			ps.setString(2, "Sanchez");
			ps.setInt(3, 15);
			ps.setString(4, "0505050505");

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
