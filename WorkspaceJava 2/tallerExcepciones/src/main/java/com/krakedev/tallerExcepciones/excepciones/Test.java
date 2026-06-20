package com.krakedev.tallerExcepciones.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String telefono = "12345891";

		try {
			ValidarContacto.validarTelefono(telefono);
			
			GuardarContactos.guardarContacto("Maria4", "Gomez", telefono);
			log.info("Contacto guardado con exito.");
			LeerContacto.leerContacto("contacto.txt");
			
		} catch (IllegalArgumentException e) {
			log.error("Error: " + e.getMessage());
		}

	}

}
