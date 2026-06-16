package com.krakedev.artesanal.testNegocio;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocio {

	public static void main(String[] args) {
		Maquina nueva=new Maquina("Cerveza Club", "Cerveza fria", 0.02, 8000, "1");
		Negocio negocio1 = new Negocio("Mi negocio", nueva);

		System.out.println("Nombre: " + negocio1.getNombre());
		System.out.println("Maquina: " + negocio1.getMaquinaA());

		Maquina m1 = negocio1.getMaquinaA();
		double capacidad = m1.getCapacidadMaxima();

		//Test negocio mejorado
		NegocioMejorado n1=new NegocioMejorado();
		String codigo=n1.generarCodigo();
		System.out.println("Codigo negocio mejorado: "+codigo);
	}

}
