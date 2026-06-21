package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCliente {
	private static final Logger log = LogManager.getLogger(DeleteCliente.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = "DELETE FROM clientes WHERE cedula=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, "1111111111");
			
			int filas = ps.executeUpdate();
			
			log.info("Filas eliminadas: " + filas);
			
		} catch (Exception e) {
			log.error("Error al eliminar filas: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
	}
}
