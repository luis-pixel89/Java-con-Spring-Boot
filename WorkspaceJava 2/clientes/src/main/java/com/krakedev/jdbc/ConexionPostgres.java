package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConexionPostgres {

	private static final Logger log = LogManager.getLogger(ConexionPostgres.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con=null;
		
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres2", "postgres", "4189");
			
			log.info("Conexion exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error al conectar a la base de datos: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
	}

}
