package com.krakedev.financiero.entidades;

public class Cuenta {
	private String id;
	private double saldoActual;
	private String tipo;
	private Cliente propietario=new Cliente();

	public Cuenta(String id) {
		super();
		this.id = id;
		this.saldoActual = 0;
		this.tipo = "A";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getPropietario() {
		return propietario;
	}

	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}

	public void imprimir() {
		System.out.println("Id Cliente: " + id + " Saldo Actual: " + saldoActual + " Tipo: " + tipo + " Cedula cliente"
				+ propietario.getCedula() + " Nombre cliente: " + propietario.getNombre() + " Apellido cliente: "
				+ propietario.getApellido());
	}
}
