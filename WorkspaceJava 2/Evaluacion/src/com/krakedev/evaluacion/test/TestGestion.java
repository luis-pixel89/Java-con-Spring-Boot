package com.krakedev.evaluacion.test;

import com.krakedev.evaluacion.Animal;
import com.krakedev.evaluacion.Gato;
import com.krakedev.evaluacion.GestionAnimal;
import com.krakedev.evaluacion.Perro;

public class TestGestion {

	public static void main(String[] args) {
		Gato g1 = new Gato();
		Perro p1=new Perro();
		Animal a1=new Animal();
		
		GestionAnimal ani=new GestionAnimal();
		
		ani.alimentar(a1);
		ani.alimentar(p1);
		ani.alimentar(g1);
	}
}
