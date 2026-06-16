package com.krakedev.figuras;

public class TrianguloRectangulo extends Figura {
	private int catetoA;
	private int catetoB;
	private int hipotenusa;

	public TrianguloRectangulo(String nombre, String color, int catetoA, int catetoB) {
		super(nombre, color);
		this.catetoA = catetoA;
		this.catetoB = catetoB;
	}

	@Override
	public int calcularPerimetro() {
		hipotenusa = catetoA * catetoA + catetoB * catetoB;
		return catetoA + catetoB + hipotenusa;
	}

	@Override
	public double calcularArea() {
		return catetoA * catetoB;
	}
}
