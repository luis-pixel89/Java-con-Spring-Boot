package com.krakedev.evaluacion.test;

import com.krakedev.evaluacion.Gato;
import com.krakedev.evaluacion.Perro;

public class TestAnimal {

	public static void main(String[] args) {
		Perro p1 = new Perro();
		
		p1.dormir();
		p1.ladrar();

		System.out.println(p1);
		
		Gato g1=new Gato();
		g1.dormir();
	}
}
