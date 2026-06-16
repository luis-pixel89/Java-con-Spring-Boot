package com.krakedev.figuras;

public class Graficador {

	public void graficar(Figura figura) {
		int perimetro = figura.calcularPerimetro();
		double area = figura.calcularArea();

		System.out.println("Graficando " + figura.getNombre() + " de color " + figura.getColor() + " con perimetro "
				+ perimetro + " area: " + area);
	}
}
