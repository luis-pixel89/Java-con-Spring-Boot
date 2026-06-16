package com.krakedev.figuras;

public class Triangulo extends Figura {
	private int base;
	private int altura;

	public Triangulo(String nombre, String color, int base, int altura) {
		super(nombre, color);
		this.altura=altura;
		this.base=base;
	}

	@Override
	public int calcularPerimetro() {
		return base*altura;
	}

	@Override
	public double calcularArea() {
		return base * altura / 2;
	}

}
