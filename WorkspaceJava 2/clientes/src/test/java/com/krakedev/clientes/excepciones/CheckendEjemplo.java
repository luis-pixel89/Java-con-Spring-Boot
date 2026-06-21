package com.krakedev.clientes.excepciones;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckendEjemplo {

	public static void main(String[] args) {

		System.out.println("Inicio del programa...");
		
		try {
			FileReader archivo = new FileReader("contacto.txt");
			System.out.println("Archivo abierto correctamente.");

		} catch (FileNotFoundException e) {
			System.out.println("Error el archivo no fue encontrado: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
