package com.krakedev.herencia.test;

import com.krakedev.herencia.Hija;

public class TestPrivado {

	public static void main(String[] args) {
		Hija hija = new Hija(10,3);
		
		hija.guardarSecreto();

	}

}
