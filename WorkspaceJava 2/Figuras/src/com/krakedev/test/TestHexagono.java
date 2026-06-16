package com.krakedev.test;

import com.krakedev.figuras.Graficador;
import com.krakedev.figuras.Hexagono;
import com.krakedev.figuras.TrianguloRectangulo;

public class TestHexagono {

	public static void main(String[] args) {
		Graficador g1 = new Graficador();
		Hexagono he1=new Hexagono("Hexagono plano","rojo",5);

		g1.graficar(he1);
		

	}

}
