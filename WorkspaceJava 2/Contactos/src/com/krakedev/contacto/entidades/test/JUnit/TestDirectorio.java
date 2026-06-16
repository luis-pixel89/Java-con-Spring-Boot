package com.krakedev.contacto.entidades.test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class TestDirectorio {
	private Directorio directorio;

	@BeforeEach
	public void inicializar() {
		directorio = new Directorio();
	}

	@Test
	public void testAgregarContactoNuevoDebeRetornarTrue() {

		// Se prueba agregar un contacto nuevo al directorio
		// Resultado esperado: el método retorna true

		Contacto contacto = new Contacto();
		contacto.setNombre("Luis");
		contacto.setCelular("0999999999");

		boolean resultado = directorio.agregarContacto(contacto);

		assertTrue(resultado);
	}

	@Test
	public void testAgregarContactoDuplicadoDebeRetornarFalse() {

		// Se prueba agregar un contacto con un número ya existente
		// Resultado esperado: el método retorna false

		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Luis");
		contacto1.setCelular("0999999999");

		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Miguel");
		contacto2.setCelular("0999999999");

		directorio.agregarContacto(contacto1);

		boolean resultado = directorio.agregarContacto(contacto2);

		assertFalse(resultado);
	}

	@Test
	public void testVerificarTamanoListaDespuesDeAgregarContactos() {

		// Se prueba que el tamaño de la lista aumente correctamente
		// Resultado esperado: la lista debe contener 2 contactos

		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Luis");
		contacto1.setCelular("0991111111");

		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Carlos");
		contacto2.setCelular("0992222222");

		directorio.agregarContacto(contacto1);
		directorio.agregarContacto(contacto2);

		int cantidad = directorio.obtenerCantidadContactos();

		assertEquals(2, cantidad);
	}

	@Test
	public void testNoDebeAgregarContactosDuplicadosEnLaLista() {

		// Se prueba que no se agreguen contactos duplicados
		// Resultado esperado: la lista debe contener solo 1 contacto

		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Luis");
		contacto1.setCelular("0988888888");

		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Pedro");
		contacto2.setCelular("0988888888");

		directorio.agregarContacto(contacto1);
		directorio.agregarContacto(contacto2);

		int cantidad = directorio.obtenerCantidadContactos();

		assertEquals(1, cantidad);
	}
}
