package com.krakedev.alien.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.alien.Alien;

public class AlienExtremidadesTest {
	private Alien alien;

	@BeforeEach
	public void setUp() {
		alien = new Alien(20, "Verde"); // alien fresco antes de cada prueba
	}

	// ── Casos válidos: brazos ────────────────────────────────────────

	@Test
	public void testAgregarBrazosRetornaTrueSiHayEspacio() {
		assertTrue(alien.agregarBrazos(3));
	}

	@Test
	public void testAgregarBrazosActualizaElContador() {
		alien.agregarBrazos(4);
		assertEquals(4, alien.getNumeroBrazos());
	}

	@Test
	public void testAgregarBrazosExactamenteElLimite() {
		assertTrue(alien.agregarBrazos(10)); // llena justo el límite
		assertEquals(10, alien.getNumeroBrazos());
	}

	// ── Casos válidos: piernas ───────────────────────────────────────

	@Test
	public void testAgregarPiernasRetornaTrueSiHayEspacio() {
		assertTrue(alien.agregarPiernas(5));
	}

	@Test
	public void testAgregarPiernasActualizaElContador() {
		alien.agregarPiernas(6);
		assertEquals(6, alien.getNumeroPies());
	}

	@Test
	public void testAgregarPiernasExactamenteElLimite() {
		assertTrue(alien.agregarPiernas(10));
		assertEquals(10, alien.getNumeroPies());
	}

	// ── Casos límite: excede el máximo ───────────────────────────────

	@Test
	public void testAgregarBrazosExcedeLimiteRetornaFalse() {
		assertFalse(alien.agregarBrazos(11));
	}

	@Test
	public void testAgregarBrazosNoSeAgregaSiExcedeLimite() {
		alien.agregarBrazos(11);
		assertEquals(0, alien.getNumeroBrazos()); // no cambió
	}

	@Test
	public void testAgregarPiernasExcedeLimiteRetornaFalse() {
		assertFalse(alien.agregarPiernas(11));
	}

	@Test
	public void testAgregarPiernasNoSeAgregaSiExcedeLimite() {
		alien.agregarPiernas(11);
		assertEquals(0, alien.getNumeroPies()); // no cambió
	}

	// ── Casos combinados ─────────────────────────────────────────────

	@Test
	public void testAgregarBrazosYPiernasEntreLimite() {
		alien.agregarBrazos(5);
		assertTrue(alien.agregarPiernas(5)); // 5+5 = 10, exacto
		assertEquals(5, alien.getNumeroBrazos());
		assertEquals(5, alien.getNumeroPies());
	}

	@Test
	public void testPiernasExcedenLimiteLuegoDeAgregarBrazos() {
		alien.agregarBrazos(7); // quedan 3 espacios libres
		assertFalse(alien.agregarPiernas(4)); // 7+4=11, excede
		assertEquals(0, alien.getNumeroPies()); // no se agregaron
	}

	@Test
	public void testBrazosExcedenLimiteLuegoDeAgregarPiernas() {
		alien.agregarPiernas(6); // quedan 4 espacios libres
		assertFalse(alien.agregarBrazos(5)); // 6+5=11, excede
		assertEquals(0, alien.getNumeroBrazos()); // no se agregaron
	}

	@Test
	public void testAgregarEnDosTurnosSinExceder() {
		alien.agregarBrazos(3); // total: 3
		alien.agregarBrazos(3); // total: 6
		assertTrue(alien.agregarPiernas(4)); // total: 10, exacto
	}

	@Test
	public void testAgregarEnDosTurnosExcediendo() {
		alien.agregarBrazos(5);
		alien.agregarPiernas(5); // total: 10, límite
		assertFalse(alien.agregarBrazos(1)); // ya no cabe nada más
	}
}
