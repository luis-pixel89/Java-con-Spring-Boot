package com.krakedev.test;

import com.krakedev.figuras.Cuadrado;
import com.krakedev.figuras.Figura;
import com.krakedev.figuras.Rectangulo;
import com.krakedev.figuras.Triangulo;

public class TestFiguras {

	public static void main(String[] args) {
		Figura f1 = new Rectangulo("Circulo", "Negro");
		Cuadrado c1=new Cuadrado("Cuadrado 1", "Azul");
		Triangulo t1 = new Triangulo("Triangulo 1", "Rojo",2,5);
		
		System.out.println(f1);
		System.out.println(c1);
		System.out.println(t1);

	}

}
