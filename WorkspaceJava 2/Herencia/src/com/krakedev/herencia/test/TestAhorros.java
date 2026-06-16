package com.krakedev.herencia.test;

import com.krakedev.herencia.Hija;
import com.krakedev.herencia.Hijo;
import com.krakedev.herencia.Padre;

public class TestAhorros {

	public static void main(String[] args) {
		Padre padre = new Padre(1,2,"Alberto");

		padre.ahorrar(200);
		System.out.println(padre);

		Hija hija = new Hija(1,2);
		hija.setNombre("Maria");
		hija.ahorrar(200);
		System.out.println(hija);

		Hijo hijo = new Hijo(2,2, "Jose",2);
		hijo.ahorrar(200);
		System.out.println(hijo);

	}

}
