package com.krakedev.clientes.excepciones;

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EscribirArchivo {
	private static final Logger log = LoggerFactory.getLogger(EscribirArchivo.class);

	public static void main(String[] args) {

		try {
			FileWriter escritor = new FileWriter("contacto.txt", true);

			escritor.write("Carlos\n");
			escritor.write("Castillo\n");
			escritor.write("1234567891\n");

			escritor.close();
			
			log.info("Archivo creado con exito.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Ocurrio un error ", e.getMessage());
		}
	}
}
