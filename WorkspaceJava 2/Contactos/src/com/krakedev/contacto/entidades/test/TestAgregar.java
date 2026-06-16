package com.krakedev.contacto.entidades.test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestAgregar {

	public static void main(String[] args) {
		Directorio dir=new Directorio();
		
		Contacto c1=new Contacto();
		c1.setNombre("Ana");
		
		dir.agregarContacto(c1);
	
	}

}
