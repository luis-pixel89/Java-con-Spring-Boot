package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class MaquinaVaciarTest {
	private Maquina maquina;

	@BeforeEach
	public void setUp() {
		maquina = new Maquina("IPA", "Cerveza artesanal", 0.05, 5000, "M001");
	}

	// ═══════════════════════════════════════════════════════════════════
	// 1. CANTIDAD ACTUAL QUEDA EN CERO
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testVaciarMaquinaDejaCantidadActualEnCero() {
		maquina.llenarMaquina(); // cantidadActual = 4800
		maquina.vaciarMaquina();
		assertEquals(0, maquina.getCantidadActual(), 0.001);
	}

	@Test
	public void testVaciarMaquinaYaVaciaNoModificaCantidad() {
		// maquina empieza en 0, vaciar no debería causar errores
		maquina.vaciarMaquina();
		assertEquals(0, maquina.getCantidadActual(), 0.001);
	}

	@Test
	public void testVaciarMaquinaConCargaParcial() {
		maquina.recargarCerveza(1000); // cantidadActual = 1000
		maquina.vaciarMaquina();
		assertEquals(0, maquina.getCantidadActual(), 0.001);
	}

	// ═══════════════════════════════════════════════════════════════════
	// 2. DESPERDICIO SE REGISTRA CORRECTAMENTE
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testVaciarMaquinaRegistraDesperdicioConLlenarMaquina() {
		maquina.llenarMaquina(); // cantidadActual = capacidadMaxima - 200 = 4800
		double esperado = maquina.getCantidadActual();
		maquina.vaciarMaquina();
		assertEquals(esperado, maquina.getCantidadDesperdicio(), 0.001);
	}

	@Test
	public void testVaciarMaquinaRegistraDesperdicioConCargaParcial() {
		maquina.recargarCerveza(2500);
		maquina.vaciarMaquina();
		assertEquals(2500, maquina.getCantidadDesperdicio(), 0.001);
	}

	@Test
	public void testVaciarMaquinaVaciaRegistraDesperdicioEnCero() {
		maquina.vaciarMaquina(); // cantidadActual era 0
		assertEquals(0, maquina.getCantidadDesperdicio(), 0.001);
	}

	// ═══════════════════════════════════════════════════════════════════
	// 3. DESPERDICIO = CANTIDAD ANTES DE VACIAR
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testDesperdicioEsIgualACantidadAntesDeVaciar() {
		maquina.recargarCerveza(3000);
		double cantidadAntes = maquina.getCantidadActual();
		maquina.vaciarMaquina();
		assertEquals(cantidadAntes, maquina.getCantidadDesperdicio(), 0.001);
	}

	@Test
	public void testDesperdicioLuegoDeServirYVaciar() {
		maquina.recargarCerveza(2000);
		maquina.servirCerveza(500); // cantidadActual = 1500
		maquina.vaciarMaquina();
		// desperdicio debe ser lo que quedaba: 1500
		assertEquals(1500, maquina.getCantidadDesperdicio(), 0.001);
	}

	// ═══════════════════════════════════════════════════════════════════
	// 4. COMPORTAMIENTO DESPUÉS DE VACIAR
	// ═══════════════════════════════════════════════════════════════════

	@Test
	public void testPuedeRecargarLuegoDeVaciar() {
		maquina.llenarMaquina();
		maquina.vaciarMaquina();
		assertTrue(maquina.recargarCerveza(1000)); // debe poder recargarse
		assertEquals(1000, maquina.getCantidadActual(), 0.001);
	}

	@Test
	public void testServirLuegoDeVaciarRetornaCero() {
		maquina.recargarCerveza(1000);
		maquina.vaciarMaquina();
		// no hay cerveza, servir debe retornar 0
		assertEquals(0, maquina.servirCerveza(500), 0.001);
	}

	@Test
	public void testVaciarDosVecesActualizaDesperdicioAlUltimoValor() {
		maquina.recargarCerveza(3000);
		maquina.vaciarMaquina(); // desperdicio = 3000

		maquina.recargarCerveza(1000);
		maquina.vaciarMaquina(); // desperdicio se sobreescribe con 1000

		assertEquals(1000, maquina.getCantidadDesperdicio(), 0.001);
		assertEquals(0, maquina.getCantidadActual(), 0.001);
	}
}
