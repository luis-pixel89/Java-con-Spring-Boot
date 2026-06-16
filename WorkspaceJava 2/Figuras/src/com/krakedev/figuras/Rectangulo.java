package com.krakedev.figuras;

public class Rectangulo extends Figura {
	private int base;
	private int altura;

	public Rectangulo(String nombre, String color, int base, int altura) {
		super(nombre, color);
		this.base = base;
		this.altura = altura;
	}

	public Rectangulo(String nombre, String color) {
		super(nombre, color);
	}

	@Override
	public int calcularPerimetro() {
		return 2 * base * 2 * altura;
	}

	@Override
	public double calcularArea() {
		return base * altura;
	}
}
