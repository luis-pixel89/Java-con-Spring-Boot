package com.krakedev.tallerExcepciones.excepciones;

public class ValidarContacto {

	public static void validarTelefono(String telefono) throws IllegalArgumentException {
		if (telefono.length() != 10) {
			throw new IllegalArgumentException("El telefono debe tener 10 digitos");
		}

	}
}
