package com.krakedev.herencia.test;

import com.krakedev.herencia.Hija;

public class TestHerencia {
	public static void main(String[] args) {
		Hija hija = new Hija(2,3);

		hija.setVirtudes(5);
		hija.setDefectos(2);

		System.out.println("Virtudes: " + hija.getVirtudes());
		System.out.println("Defectos: " + hija.getDefectos());
		
		hija.imprimir();
		
	}
}
