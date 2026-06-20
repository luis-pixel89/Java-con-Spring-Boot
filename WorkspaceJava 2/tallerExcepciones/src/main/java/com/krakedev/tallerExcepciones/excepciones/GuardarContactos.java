package com.krakedev.tallerExcepciones.excepciones;

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuardarContactos {
	private static final Logger log = LoggerFactory.getLogger(GuardarContactos.class);

	public static void guardarContacto(String nombre, String apellido, String telefono) {
		FileWriter escritor = null;
		try {
			escritor = new FileWriter("contacto.txt", true);

			escritor.write(nombre + "\n");
			escritor.write(apellido + "\n");
			escritor.write(telefono + "\n");

			log.info("Archivo creado con exito.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Ocurrio un error ", e.getMessage());
		} finally {
			try {
				if (escritor != null) {
					escritor.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar el escritor ", e.getMessage());
			}
		}
	}
}
