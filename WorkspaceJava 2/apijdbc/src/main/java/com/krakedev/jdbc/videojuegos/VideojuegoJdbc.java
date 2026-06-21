package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {
	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {
		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();

			String sql = "INSERT INTO videojuegos (codigo, nombre, plataforma, precio, disponible, genero) VALUES (?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);

			int filas = ps.executeUpdate();

			log.info("Filas insertadas: " + filas);

		} catch (Exception e) {
			log.error("Error al insertar: " + e.getMessage());
			throw new RuntimeException("Error al insertar el videojuego: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return videojuego;
	}
	
	public static List<Videojuego>listar(){
		List<Videojuego> videojuegos = new ArrayList<>();
		Connection con = null;
		
		try {
			con = Conexion.getConnection();
			
			String sql = "SELECT * FROM videojuegos";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				String plataforma = rs.getString("plataforma");
				double precio = rs.getDouble("precio");
				boolean disponible = rs.getBoolean("disponible");
				String genero = rs.getString("genero");
				
				videojuegos.add(new Videojuego(codigo, nombre, plataforma, precio, disponible, genero));
			}
			
		} catch (Exception e) {
			log.error("Error al listar: " + e.getMessage());
			throw new RuntimeException("Error al listar los videojuegos: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		
		return videojuegos;	
	}
	
	public static Videojuego buscar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM videojuegos WHERE codigo=?";
		ResultSet rs = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String plataforma = rs.getString("plataforma");
				double precio = rs.getDouble("precio");
				boolean disponible = rs.getBoolean("disponible");
				String genero = rs.getString("genero");

				videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			}
		} catch (Exception e) {
			log.error("Error al buscar: " + e.getMessage());
			throw new RuntimeException("Error al buscar el videojuego: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return videojuego;
	}
	
	public static Videojuego actualizar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {
		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();

			String sql = "UPDATE videojuegos SET nombre=?, plataforma=?, precio=?, disponible=?, genero=? WHERE codigo=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, plataforma);
			ps.setDouble(3, precio);
			ps.setBoolean(4, disponible);
			ps.setString(5, genero);
			ps.setString(6, codigo);

			videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);

			int filas = ps.executeUpdate();

			log.info("Filas actualizadas: " + filas);

		} catch (Exception e) {
			log.error("Error al actualizar: " + e.getMessage());
			throw new RuntimeException("Error al actualizar el videojuego: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return videojuego;
	}
	
	public static boolean eliminar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM videojuegos WHERE codigo=?";
		boolean eliminado = false;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			int filas = ps.executeUpdate();

			log.info("Filas eliminadas: " + filas);

			eliminado = true;

		} catch (Exception e) {
			log.error("Error al eliminar: " + e.getMessage());
			throw new RuntimeException("Error al eliminar el videojuego: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.error("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return eliminado;
	}
}
