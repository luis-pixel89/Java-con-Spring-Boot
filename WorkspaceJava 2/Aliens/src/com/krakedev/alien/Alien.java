package com.krakedev.alien;

public class Alien {
	private int tamanio;
	private String color;
	private int numeroOjos;
	private int numeroBrazos;
	private int numeroPies;
	private double precioExtremidad;
	private double precioOjo;
	private double precioCuerpo;
	private double precioTotal = 0;

	private static final int TAMANIO_MIN = 5;
	private static final int TAMANIO_MAX = 30;

	public Alien(int tamanio, String color) {
		super();

		if (tamanio < TAMANIO_MIN) {
			this.tamanio = TAMANIO_MIN;
		} else if (tamanio > TAMANIO_MAX) {
			this.tamanio = TAMANIO_MAX;
		} else {
			this.tamanio = tamanio;
		}

		this.color = color;

		this.precioCuerpo = this.tamanio * 0.20;
		this.precioExtremidad = this.tamanio * 0.10;
		this.precioOjo = this.tamanio * 0.05;
	}

	public int getTamanio() {
		return tamanio;
	}

	public String getColor() {
		return color;
	}

	public int getNumeroOjos() {
		return numeroOjos;
	}

	public int getNumeroBrazos() {
		return numeroBrazos;
	}

	public int getNumeroPies() {
		return numeroPies;
	}

	public double getPrecioExtremidad() {
		return precioExtremidad;
	}

	public double getPrecioOjo() {
		return precioOjo;
	}

	public double getPrecioCuerpo() {
		return precioCuerpo;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void imprimir() {
		System.out.println("=== Alien ===\n" + "Tamaño: " + tamanio + " cm\n" + "Color: " + color + "\n" + "Ojos: "
				+ numeroOjos + "\n" + "Brazos: " + numeroBrazos + "\n" + "Pies: " + numeroPies + "\n"
				+ "Precio cuerpo: $" + precioCuerpo + "\n" + "Precio extremidad: $" + precioExtremidad + "\n"
				+ "Precio ojo: $" + precioOjo);
	}

	private static final int MAX_EXTREMIDADES = 10;

	public boolean agregarBrazos(int cantidad) {
		if (numeroBrazos + numeroPies + cantidad > MAX_EXTREMIDADES) {
			return false;
		}
		numeroBrazos += cantidad;
		calcularPrecioTotal();
		return true;
	}

	public boolean agregarPiernas(int cantidad) {
		if (numeroBrazos + numeroPies + cantidad > MAX_EXTREMIDADES) {
			return false;
		}
		numeroPies += cantidad;
		calcularPrecioTotal();
		return true;
	}

	public boolean agregarOjos(int cantidad) {
		int maxOjos = obtenerMaxOjos();

		if (numeroOjos + cantidad > maxOjos)
			return false;

		numeroOjos += cantidad;
		calcularPrecioTotal();
		return true;
	}

	private int obtenerMaxOjos() {
		if (tamanio <= 10) {
			return 3;
		} else if (tamanio <= 20) {
			return 5;
		} else {
			return 7;
		}
	}

	private void calcularPrecioTotal() {
		precioTotal = precioCuerpo 
				+ (numeroBrazos + numeroPies) * precioExtremidad 
				+ (numeroOjos * precioOjo);
	}

}
