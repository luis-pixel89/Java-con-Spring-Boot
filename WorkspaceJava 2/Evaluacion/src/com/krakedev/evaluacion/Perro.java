package com.krakedev.evaluacion;

public class Perro extends Animal {
	public void ladrar() {
		System.out.println("Perro ladrando");
	}
	
	@Override
	public void dormir() {
		System.out.println("perro durmiendo");
	}
}
