package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	ArrayList<Maquina> maquinas = new ArrayList<>();
	ArrayList<Cliente> clientes = new ArrayList<>();

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public String generarCodigo() {
		int numero = (int) (Math.random() * 100) + 1;
		return "M-" + numero;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public boolean agregarMaquina(String nombre, String descripcion, double precioPorMl) {
		String codigo = generarCodigo();

		Maquina maquinaRecuperada = recuperarMaquina(codigo);

		if (maquinaRecuperada == null) {
			Maquina m1 = new Maquina(nombre, descripcion, precioPorMl, codigo);
			maquinas.add(m1);
			return true;
		} else {
			return false;
		}
	}

	public void cargarMaquinas() {
		for (int i = 0; i < maquinas.size(); i++) {
			Maquina m1 = maquinas.get(i);
			m1.llenarMaquina();
		}
	}

	public Maquina recuperarMaquina(String codigo) {
		for (int i = 0; i < maquinas.size(); i++) {
			Maquina m1 = maquinas.get(i);
			if (m1.getCodigo().equals(codigo)) {
				return m1;
			}
		}
		return null;
	}

	public void registrarCliente(String nombre, String cedula) {
		int ultimoCodigo = 100;

		Cliente c1 = new Cliente(nombre, cedula);
		clientes.add(c1);

		ultimoCodigo++;
	}

	public Cliente buscarClientePorCedula(String cedula) {
		for (int i = 0; i < clientes.size(); i++) {
			Cliente clienteEncontrado = clientes.get(i);
			if (clienteEncontrado.getCedula().equals(cedula)) {
				return clienteEncontrado;
			}
		}
		return null;
	}

	public Cliente buscarClientePorCodigo(int codigo) {
		for (int i = 0; i < clientes.size(); i++) {
			Cliente clienteEncontrado = clientes.get(i);
			if (clienteEncontrado.getCodigo() == codigo) {
				return clienteEncontrado;
			}
		}
		return null;
	}

	public void consumirCerveza(int codigoCliente, String codigoMaquina, double cantidad) {
		Maquina maquinaRecuperada = recuperarMaquina(codigoMaquina);
		Cliente clienteRecuperado = buscarClientePorCodigo(codigoCliente);
		double cervezaCervida = maquinaRecuperada.servirCerveza(cantidad);
		
		registrarConsumo(clienteRecuperado, cantidad);
	}
	
	public void registrarConsumo(Cliente cliente, double cantidad) {
		cliente.setTotalConsumido(cliente.getTotalConsumido()+cantidad);;
	}
	
	public double consultarValorVendido() {
		double totalConsumido=0;
		
		for(int i=0; i<clientes.size(); i++) {
			Cliente c=clientes.get(i);
			totalConsumido+=c.getTotalConsumido();
		}
		
		return totalConsumido;
	}
}
