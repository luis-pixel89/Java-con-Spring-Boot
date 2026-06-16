package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejoradoCargarMaquinas {
	@Test
	public void testCargarMaquinas_DebeLlenarTodasLasMaquinas() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Sprite", "Limón", 0.15);
		negocio.agregarMaquina("Inca", "Amarilla", 0.18);

		negocio.cargarMaquinas();

		Maquina m1 = negocio.getMaquinas().get(0);
		Maquina m2 = negocio.getMaquinas().get(1);

		/*
		 * Se asume que llenarMaquina() deja capacidadActual en un valor mayor a 0.
		 */

		assertTrue(m1.getCantidadActual() > 0);
		assertTrue(m2.getCantidadActual() > 0);
	}
}
