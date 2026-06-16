package com.krakedev.herencia;

public class Padre {
	private int defectos;
	private int virtudes;
	private double totalAhorrado;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotalAhorrado() {
		return totalAhorrado;
	}

	public void setTotalAhorrado(double totalAhorrado) {
		this.totalAhorrado = totalAhorrado;
	}

	@Override
	public String toString() {
		return "Padre [defectos=" + defectos + ", virtudes=" + virtudes + ", total ahorrado: " + totalAhorrado
				+ " Nombre: " + nombre + "]";
	}

	public Padre() {
		// System.out.println("Soy el constructor vacio del Padre");
	}

	public Padre(int defectos, int virtudes, String nombre) {
		super();
		this.defectos = defectos;
		this.virtudes = virtudes;
		this.nombre = nombre;
	}

	public Padre(int defectos, int virtudes) {

		this.defectos = defectos;
		this.virtudes = virtudes;
	}

	public int getDefectos() {
		return defectos;
	}

	public void setDefectos(int defectos) {
		this.defectos = defectos;
	}

	public int getVirtudes() {
		return virtudes;
	}

	public void setVirtudes(int virtudes) {
		this.virtudes = virtudes;
	}

	public void imprimir() {
		System.out.println("Virtudes: " + getVirtudes());
		System.out.println("Defectos: " + getDefectos());
	}

	public void guardarSecreto() {
		System.out.println("Esto no se hereda");
	}

	public void ahorrar(double monto) {
		totalAhorrado += monto;
	}
}
