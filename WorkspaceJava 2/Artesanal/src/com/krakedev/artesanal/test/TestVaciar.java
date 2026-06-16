package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestVaciar {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("Pilsener", "cerveza rubia",0.02,1000,"1");
		rubia.llenarMaquina();
		rubia.imprimir();
		
		rubia.servirCerveza(100);
		rubia.imprimir();
		
		rubia.vaciarMaquina();
		rubia.imprimir();
		
		
	}

}
