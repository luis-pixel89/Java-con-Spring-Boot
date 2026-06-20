package com.krakedev.clientes.excepciones;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropagaciondeError {

	public void metodoA() throws FileNotFoundException {
		FileReader archivo = new FileReader("archivo.txt");
		System.out.println("Archivo abierto correctamente.");
	}
	
	public void metodoB(){
		try {
			metodoA();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
