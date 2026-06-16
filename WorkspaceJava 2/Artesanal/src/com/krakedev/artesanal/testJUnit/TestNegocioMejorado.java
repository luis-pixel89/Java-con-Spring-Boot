package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejorado {
	@Test
	public void testGenerarCodigo_NoDebeSerNull() {

		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		assertNotNull(codigo);
	}

	@Test
	public void testGenerarCodigo_DebeIniciarConMGuion() {

		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		assertTrue(codigo.startsWith("M-"));
	}

	@Test
	public void testGenerarCodigo_DebeTenerFormatoCorrecto() {

		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		assertTrue(codigo.matches("M-\\d+"));
	}

	@Test
	public void testGenerarCodigo_NumeroDebeEstarEntre1y100() {

		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		String parteNumerica = codigo.substring(2);

		int numero = Integer.parseInt(parteNumerica);

		assertTrue(numero >= 1 && numero <= 100);
	}

	@Test
	public void testGenerarCodigo_GeneraValoresDiferentes() {

		NegocioMejorado negocio = new NegocioMejorado();

		String codigo1 = negocio.generarCodigo();
		String codigo2 = negocio.generarCodigo();

		/*
		 * Esta prueba puede fallar ocasionalmente porque Math.random puede repetir
		 * números. Se incluye solo como ejemplo de variabilidad.
		 */

		assertNotEquals(codigo1, codigo2);
	}
}
