package com.krakedev.contacto.entidades;

import java.util.ArrayList;

public class Directorio {
	ArrayList<Contacto> contactos;

	public Directorio() {
		contactos = new ArrayList<Contacto>();
	}

	public ArrayList<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(ArrayList<Contacto> contactos) {
		this.contactos = contactos;
	}

	public boolean agregarContacto(Contacto contacto) {
		Contacto existente = buscarContacto(contacto.getCelular());

		if (existente == null) {
			contactos.add(contacto);
			return true;
		} else {
			return false;
		}
	}

	public int obtenerCantidadContactos() {
		int cantidad;

		cantidad = contactos.size();
		return cantidad;
	}

	public Contacto obtenerContacto(int posicion) {
		return contactos.get(posicion);
	}

	public void imprimirContactos() {
		for (int i = 0; i < contactos.size(); i++) {
			Contacto contacto = contactos.get(i);

			System.out.println("Nombre: " + contacto.getNombre());
		}
	}

	public String recuperarNumero(int posicion) {
		Contacto c = contactos.get(posicion);

		String numero = c.getCelular();

		return numero;
	}
	/*
	 * public Contacto buscarContacto(String numero) { Contacto encontrado = null;
	 * 
	 * for (int i = 0; i < contactos.size(); i++) {
	 * 
	 * 
	 * Contacto c = contactos.get(i); if (c.getCelular().equals(numero)) {
	 * encontrado = c; break; } } return encontrado; }
	 */

	public Contacto buscarContacto(String numero) {
		Contacto encontrado = null;

		for (Contacto c : contactos) {
			if (c.getCelular().equals(numero)) {
				encontrado = c;
				break;
			}
		}

		return encontrado;
	}

	public boolean eliminarContacto(String numero) {
		Contacto encontrado = buscarContacto(numero);

		if (encontrado == null) {
			return false;
		} else {
			contactos.remove(encontrado);
			return true;
		}
	}

	public ArrayList<Contacto> buscarContactosCoincidencia(String subcadena) {
		ArrayList<Contacto> encontrados = new ArrayList<Contacto>();

		for (int i = 0; i < contactos.size(); i++) {
			Contacto c = contactos.get(i);

			if (c.getNombre().startsWith(subcadena)) {
				encontrados.add(c);
			}
		}
		return encontrados;
	}

}
