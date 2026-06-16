package com.krakedev.test;

import com.krakedev.figuras.Cuadrado;
import com.krakedev.figuras.Figura;
import com.krakedev.figuras.Graficador;
import com.krakedev.figuras.Rectangulo;
import com.krakedev.figuras.TrianguloRectangulo;

public class TestGraficar {

	public static void main(String[] args) {
		Graficador g1 = new Graficador();
		Figura f1 = new Cuadrado("Figura", "negro");
		Cuadrado c1 = new Cuadrado("Cuadrado", "rojo",2);
		Rectangulo r1 = new Rectangulo("Rectangulo", "rojo",4,8);

		g1.graficar(f1);
		g1.graficar(c1);
		g1.graficar(r1);
		
		TrianguloRectangulo tr1=new TrianguloRectangulo("Triangulo rectangulo", "negro",4,8);
		g1.graficar(tr1);

	}

}
