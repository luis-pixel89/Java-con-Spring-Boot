package com.krakedev.test;

import com.krakedev.figuras.Cuadrado;
import com.krakedev.figuras.Rectangulo;

public class TestPerimetro {

	public static void main(String[] args) {
		Rectangulo r1 = new Rectangulo("Rectangulo 1", "negro", 2, 4);
		int perimetroRectangulo = r1.calcularPerimetro();

		System.out.println("Perimetro del rectangulo: " + perimetroRectangulo);

		Cuadrado c1 = new Cuadrado("Cuadrado 1", "rojo", 2);
		int perimetroCuadrado = c1.calcularPerimetro();
		System.out.println("Perimetro del cuadrado: " + perimetroCuadrado);

	}

}
