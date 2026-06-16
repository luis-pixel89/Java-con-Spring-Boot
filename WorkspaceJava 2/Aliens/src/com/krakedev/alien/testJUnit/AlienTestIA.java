package com.krakedev.alien.testJUnit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.krakedev.alien.Alien;

public class AlienTestIA {
	// ── Pruebas parametrizadas de tamaños válidos ────────────────────

	@ParameterizedTest
	@ValueSource(ints = { 5, 10, 15, 20, 25, 30 })
	public void testTamaniosValidosNoSonAjustados(int tamanio) {
		Alien alien = new Alien(tamanio, "Verde");
		assertEquals(tamanio, alien.getTamanio());
	}

	// ── Pruebas parametrizadas de ajuste automático ──────────────────

	@ParameterizedTest
	@CsvSource({ "0,  5", "-5, 5", "-100, 5", "31, 30", "100, 30", "999, 30" })
	public void testTamanioFueraDeRangoEsAjustado(int entrada, int esperado) {
		Alien alien = new Alien(entrada, "Azul");
		assertEquals(esperado, alien.getTamanio());
	}

	// ── Pruebas parametrizadas de precios ────────────────────────────

	@ParameterizedTest
	@CsvSource({ "10, 2.0,  1.0,  0.5", "20, 4.0,  2.0,  1.0", "30, 6.0,  3.0,  1.5", "5,  1.0,  0.5,  0.25" })
	public void testCalculoDePreciosSegunTamanio(int tamanio, double cuerpo, double extremidad, double ojo) {

		Alien alien = new Alien(tamanio, "Rojo");
		assertAll(() -> assertEquals(cuerpo, alien.getPrecioCuerpo(), 0.001),
				() -> assertEquals(extremidad, alien.getPrecioExtremidad(), 0.001),
				() -> assertEquals(ojo, alien.getPrecioOjo(), 0.001));
	}

	// ── Color nulo y vacío ───────────────────────────────────────────

	@Test
	public void testColorVacioEsAceptado() {
		Alien alien = new Alien(15, "");
		assertEquals("", alien.getColor());
	}

	@Test
	public void testColorNuloEsAceptado() {
		Alien alien = new Alien(15, null);
		assertNull(alien.getColor());
	}

	// ── Precio nunca negativo ────────────────────────────────────────

	@Test
	public void testPreciosNuncaSonNegativos() {
		Alien alien = new Alien(-999, "Gris"); // se ajusta a 5
		assertAll(() -> assertTrue(alien.getPrecioCuerpo() >= 0), () -> assertTrue(alien.getPrecioExtremidad() >= 0),
				() -> assertTrue(alien.getPrecioOjo() >= 0));
	}

	// ── Consistencia entre precios ───────────────────────────────────

	@Test
	public void testPrecioCuerpoEsMayorQuePrecioExtremidad() {
		Alien alien = new Alien(20, "Verde");
		assertTrue(alien.getPrecioCuerpo() > alien.getPrecioExtremidad());
	}

	@Test
	public void testPrecioExtremidadEsMayorQuePrecioOjo() {
		Alien alien = new Alien(20, "Verde");
		assertTrue(alien.getPrecioExtremidad() > alien.getPrecioOjo());
	}
}
