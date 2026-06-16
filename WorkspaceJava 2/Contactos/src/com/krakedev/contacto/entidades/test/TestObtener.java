package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestObtener {
	public static void main(String[] args) {
		Directorio dir = new Directorio();

		Contacto c1 = new Contacto();
		c1.setNombre("Maria");

		Contacto c2 = new Contacto();
		c2.setNombre("Juan");

		Contacto c3 = new Contacto();
		c3.setNombre("Carlos");

		dir.agregarContacto(c1);
		dir.agregarContacto(c2);
		dir.agregarContacto(c3);
		
		Contacto contactoRecuperado;
		
		contactoRecuperado=dir.obtenerContacto(0);
		System.out.println("Nombre: "+contactoRecuperado.getNombre());
	}
}
