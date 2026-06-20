package com.krakedev.tallerExcepciones.excepciones;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeerContacto {
	private static final Logger log = LogManager.getLogger(LeerContacto.class);

	public static void leerContacto(String archivo) {
		BufferedReader lector = null;

		FileReader lectorArchivo;
		try {
			lectorArchivo = new FileReader(archivo);

			lector = new BufferedReader(lectorArchivo);

			for (int i = 0; i < archivo.length(); i++) {
				log.info(lector.readLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Archivo no encontrado "+ e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error al leer el archivo "+ e.getMessage());
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar el lector "+ e.getMessage());
			}
		}

	}
}
