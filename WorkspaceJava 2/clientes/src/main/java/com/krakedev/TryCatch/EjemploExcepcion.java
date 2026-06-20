package com.krakedev.TryCatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EjemploExcepcion {

	private static final Logger log = LogManager.getLogger(EjemploExcepcion.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int a = 10 / 0;

		} catch (Exception e) {
			log.error("Ocurrio un error ", e);
		}

		log.info("El programa se sigue ejecutando...");
	}

}
