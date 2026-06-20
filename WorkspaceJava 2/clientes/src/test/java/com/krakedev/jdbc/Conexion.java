package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger log = LogManager.getLogger(Conexion.class);
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres2";
	private static final String USER = "postgres";
	private static final String PASSWORD = "4189";

	public static Connection getConnection() {
		// TODO Auto-generated method stub

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			log.info("Conexion exitosa");

			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error al conectar a la base de datos: " + e.getMessage());
			throw new RuntimeException("Error al conectar a la base de datos", e);
		}
	}
}
