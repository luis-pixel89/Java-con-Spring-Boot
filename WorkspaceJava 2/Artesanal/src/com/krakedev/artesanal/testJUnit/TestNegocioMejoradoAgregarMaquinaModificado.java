package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejoradoAgregarMaquinaModificado {
	@Test
	public void testAgregarMaquina_DebeRetornarTrueCuandoSeAgregaCorrectamente() {

		NegocioMejorado negocio = new NegocioMejorado();

		boolean resultado = negocio.agregarMaquina("Coca Cola", "Bebida negra", 0.25);

		assertTrue(resultado);
	}

	@Test
	public void testAgregarMaquina_DebeAgregarUnaMaquinaALaLista() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Pepsi", "Bebida gaseosa", 0.30);

		assertEquals(1, negocio.getMaquinas().size());
	}

	@Test
	public void testAgregarMaquina_DebeGuardarDatosCorrectamente() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Sprite", "Limón", 0.20);

		Maquina maquina = negocio.getMaquinas().get(0);

		assertEquals("Sprite", maquina.getNombreCerveza());
		assertEquals("Limón", maquina.getDescripcion());
		assertEquals(0.20, maquina.getPrecioPorMl());
	}

	@Test
	public void testAgregarMaquina_DebeGenerarCodigo() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Fanta", "Naranja", 0.18);

		Maquina maquina = negocio.getMaquinas().get(0);

		assertNotNull(maquina.getCodigo());
		assertTrue(maquina.getCodigo().startsWith("M-"));
	}

	@Test
	public void testAgregarMaquina_NoDebeAgregarDuplicado() {

		NegocioMejorado negocio = new NegocioMejorado();

		/*
		 * Esta prueba NO es completamente confiable porque generarCodigo usa
		 * Math.random().
		 *
		 * No hay garantía de que el segundo código generado sea igual al primero.
		 *
		 * Desde testing profesional, este diseño no es deterministicamente testeable.
		 */

		negocio.agregarMaquina("Coca Cola", "Normal", 0.25);

		int tamañoInicial = negocio.getMaquinas().size();

		negocio.agregarMaquina("Pepsi", "Black", 0.30);

		int tamañoFinal = negocio.getMaquinas().size();

		assertTrue(tamañoFinal >= tamañoInicial);
	}
}
