package com.krakedev.figuras;

public class Hexagono extends Figura {
	private int lado;

	public Hexagono(String nombre, String color, int lado) {
		super(nombre, color);
		this.lado = lado;
	}

	@Override
	public int calcularPerimetro() {
		return 6 * lado;
	}

	@Override
	public double calcularArea() {
		return (3 * Math.sqrt(3) * lado * lado) / 2;
	}
}
