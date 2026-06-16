package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestEliminarContacto {
	public static void main(String[] args) {
		Directorio dir = new Directorio();

		Contacto c1 = new Contacto();
		c1.setNombre("Maria");
		c1.setCelular("0982393311");

		Contacto c2 = new Contacto();
		c2.setNombre("Juan");
		c2.setCelular("0982393312");

		Contacto c3 = new Contacto();
		c3.setNombre("Carlos");
		c3.setCelular("0982393313");

		dir.agregarContacto(c1);
		dir.agregarContacto(c2);
		dir.agregarContacto(c3);

		boolean r1 = dir.eliminarContacto("0982393312");
		System.out.println("Resultado de eliminar contacto 1: " + r1);
		System.out.println("Cantidad de contactos: " + dir.obtenerCantidadContactos());
		
		boolean r2 = dir.eliminarContacto("0982393312");
		System.out.println("Resultado de eliminar contacto 1: " + r2);
	}
}
