package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestBuscarContacto {
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

		//Caso 1 contacto encontrado
		Contacto encontrado = dir.buscarContacto("0982393311");

		if (encontrado != null) {
			System.out.println("Nombre: " + encontrado.getNombre());
		} else {
			System.out.println("No existe");
		}

		
		//Caso 2 no se encuentra el contacto
		Contacto noEncontrado = dir.buscarContacto("10982393311");

		if (noEncontrado != null) {
			System.out.println("Nombre: " + noEncontrado.getNombre());
		} else {
			System.out.println("No existe la persona con ese numero");
		}
	}
}
