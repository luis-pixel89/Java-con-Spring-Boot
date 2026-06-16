package com.krakedev.alien.testJUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.krakedev.alien.Alien;

public class AlienPruebasFinalesTest {
	private Alien alien;

	@BeforeEach
	public void setUp() {
		alien = new Alien(20, "Verde");
		// tamaño=20 | precioCuerpo=4.0 | precioExtremidad=2.0 | precioOjo=1.0
	}

	// ═══════════════════════════════════════════════════════════════════
	// 1. CONSTRUCTOR Y ESTADO INICIAL
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testEstadoInicialCompleto() {
		assertAll(() -> assertEquals(20, alien.getTamanio()), () -> assertEquals("Verde", alien.getColor()),
				() -> assertEquals(0, alien.getNumeroBrazos()), () -> assertEquals(0, alien.getNumeroPies()),
				() -> assertEquals(0, alien.getNumeroOjos()), () -> assertEquals(4.0, alien.getPrecioCuerpo(), 0.001),
				() -> assertEquals(2.0, alien.getPrecioExtremidad(), 0.001),
				() -> assertEquals(1.0, alien.getPrecioOjo(), 0.001),
				() -> assertEquals(0.0, alien.getPrecioTotal(), 0.001));
	}

	@Test
	public void testTamanioMenorAlMinimoSeAjusta() {
		Alien a = new Alien(2, "Azul");
		assertEquals(5, a.getTamanio());
	}

	@Test
	public void testTamanioMayorAlMaximoSeAjusta() {
		Alien a = new Alien(99, "Rojo");
		assertEquals(30, a.getTamanio());
	}

	// ═══════════════════════════════════════════════════════════════════
	// 2. PRECIOS SEGÚN TAMAÑO
	// ═══════════════════════════════════════════════════════════════════

	@ParameterizedTest
	@CsvSource({ "5,  1.0,  0.5,  0.25", "10, 2.0,  1.0,  0.5", "20, 4.0,  2.0,  1.0", "30, 6.0,  3.0,  1.5" })
	public void testPreciosSegunTamanio(int tamanio, double cuerpo, double extremidad, double ojo) {
		Alien a = new Alien(tamanio, "Gris");
		assertAll(() -> assertEquals(cuerpo, a.getPrecioCuerpo(), 0.001),
				() -> assertEquals(extremidad, a.getPrecioExtremidad(), 0.001),
				() -> assertEquals(ojo, a.getPrecioOjo(), 0.001));
	}

	// ═══════════════════════════════════════════════════════════════════
	// 3. RESTRICCIONES DE EXTREMIDADES
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testMaximo10ExtremidadesEntreBrazosYPiernas() {
		alien.agregarBrazos(5);
		alien.agregarPiernas(5);
		assertFalse(alien.agregarBrazos(1)); // ya está lleno
		assertFalse(alien.agregarPiernas(1)); // ya está lleno
	}

	@Test
	public void testExtremidadesNoSeAgreganSiExceden() {
		alien.agregarBrazos(8);
		alien.agregarPiernas(4); // 8+4=12, excede → no se agrega
		assertEquals(0, alien.getNumeroPies());
	}

	@Test
	public void testExtremidadesExactamenteEnElLimite() {
		assertTrue(alien.agregarBrazos(6));
		assertTrue(alien.agregarPiernas(4)); // 6+4=10, exacto
		assertEquals(10, alien.getNumeroBrazos() + alien.getNumeroPies());
	}

	// ═══════════════════════════════════════════════════════════════════
	// 4. RESTRICCIONES DE OJOS SEGÚN TAMAÑO
	// ═══════════════════════════════════════════════════════════════════

	@ParameterizedTest
	@CsvSource({ "5,  3, true", // límite exacto rango 1
			"10, 3, true", // límite exacto rango 1
			"10, 4, false", // excede rango 1
			"11, 5, true", // límite exacto rango 2
			"20, 5, true", // límite exacto rango 2
			"20, 6, false", // excede rango 2
			"21, 7, true", // límite exacto rango 3
			"30, 7, true", // límite exacto rango 3
			"30, 8, false" // excede rango 3
	})
	public void testRestriccionesDeOjosPorTamanio(int tamanio, int ojos, boolean esperado) {
		Alien a = new Alien(tamanio, "Gris");
		assertEquals(esperado, a.agregarOjos(ojos));
	}

	@Test
	public void testOjosNoSeAgreganSiExceden() {
		Alien a = new Alien(10, "Gris"); // máx 3
		a.agregarOjos(9); // excede, no se agrega
		assertEquals(0, a.getNumeroOjos());
	}

	// ═══════════════════════════════════════════════════════════════════
	// 5. CÁLCULO DE PRECIO TOTAL
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testPrecioTotalSoloBrazos() {
		alien.agregarBrazos(4);
		// 4.0 + (4+0)*2.0 + 0*1.0 = 12.0
		assertEquals(12.0, alien.getPrecioTotal(), 0.001);
	}

	@Test
	public void testPrecioTotalSoloPiernas() {
		alien.agregarPiernas(3);
		// 4.0 + (0+3)*2.0 + 0*1.0 = 10.0
		assertEquals(10.0, alien.getPrecioTotal(), 0.001);
	}

	@Test
	public void testPrecioTotalSoloOjos() {
		alien.agregarOjos(5);
		// 4.0 + 0*2.0 + 5*1.0 = 9.0
		assertEquals(9.0, alien.getPrecioTotal(), 0.001);
	}

	@Test
	public void testPrecioTotalCombinado() {
		alien.agregarBrazos(3);
		alien.agregarPiernas(2);
		alien.agregarOjos(4);
		// 4.0 + (3+2)*2.0 + 4*1.0 = 4.0 + 10.0 + 4.0 = 18.0
		assertEquals(18.0, alien.getPrecioTotal(), 0.001);
	}

	@Test
	public void testPrecioTotalNoCambiaAlFallarOperacion() {
		alien.agregarBrazos(3);
		double precioAntes = alien.getPrecioTotal();

		alien.agregarBrazos(9); // excede, falla
		alien.agregarOjos(9); // excede, falla

		assertEquals(precioAntes, alien.getPrecioTotal(), 0.001);
	}

	@Test
	public void testPrecioTotalSeRecalculaEnCadaOperacion() {
		alien.agregarBrazos(2);
		double despuesBrazos = alien.getPrecioTotal(); // 4.0 + 4.0 = 8.0

		alien.agregarPiernas(2);
		double despuesPiernas = alien.getPrecioTotal(); // 4.0 + 8.0 = 12.0

		alien.agregarOjos(3);
		double despuesOjos = alien.getPrecioTotal(); // 4.0 + 8.0 + 3.0 = 15.0

		assertAll(() -> assertEquals(8.0, despuesBrazos, 0.001), () -> assertEquals(12.0, despuesPiernas, 0.001),
				() -> assertEquals(15.0, despuesOjos, 0.001));
	}

	// ═══════════════════════════════════════════════════════════════════
	// 6. ESCENARIOS COMPLETOS DE NEGOCIO
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testAlienMaximoPosible() {
		// Alien al límite de todo
		Alien a = new Alien(30, "Dorado");
		// precioCuerpo=6.0 | precioExtremidad=3.0 | precioOjo=1.5
		a.agregarBrazos(5);
		a.agregarPiernas(5);
		a.agregarOjos(7);
		// 6.0 + (5+5)*3.0 + 7*1.5 = 6.0 + 30.0 + 10.5 = 46.5
		assertAll(() -> assertEquals(10, a.getNumeroBrazos() + a.getNumeroPies()),
				() -> assertEquals(7, a.getNumeroOjos()), () -> assertEquals(46.5, a.getPrecioTotal(), 0.001));
	}

	@Test
	public void testAlienMinimoPosible() {
		// Alien sin nada agregado con tamaño mínimo
		Alien a = new Alien(5, "Blanco");
		assertEquals(0.0, a.getPrecioTotal(), 0.001);
	}

	@Test
	public void testSecuenciaCompleta() {
		// Simula el ciclo de vida completo de un alien
		Alien a = new Alien(15, "Morado");
		// precioCuerpo=3.0 | precioExtremidad=1.5 | precioOjo=0.75

		assertTrue(a.agregarBrazos(4)); // válido
		assertTrue(a.agregarPiernas(3)); // válido, total extremidades=7
		assertFalse(a.agregarBrazos(4)); // 7+4=11, excede
		assertTrue(a.agregarOjos(5)); // válido para tamaño 15
		assertFalse(a.agregarOjos(1)); // 5+1=6 > 5, excede

		// 3.0 + (4+3)*1.5 + 5*0.75 = 3.0 + 10.5 + 3.75 = 17.25
		assertEquals(17.25, a.getPrecioTotal(), 0.001);
	}
}
