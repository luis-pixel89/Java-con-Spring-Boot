package com.krakedev.alien.test;

import com.krakedev.alien.Alien;

public class TestConstructores {

	public static void main(String[] args) {
		Alien alien1 = new Alien(15, "azul");
		alien1.imprimir();
		
		Alien alien2 = new Alien(35, "negro");
		alien2.imprimir();

	}

}
