package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger log = LogManager.getLogger(Conexion.class);
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";	// Cambia "postgres2" por el nombre de tu base de datos puerto 5432
	private static final String USER = "root";	// Cambia "postgres" por tu usuario de la base de datos
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
