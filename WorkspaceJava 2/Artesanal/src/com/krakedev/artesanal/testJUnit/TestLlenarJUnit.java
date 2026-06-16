package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestLlenarJUnit {
	@Test
	public void testLlenarMaquina() {
		Maquina rubia = new Maquina("Pilsener", "cerveza", 0.02, 8000, "1");
		rubia.llenarMaquina();
		assertEquals(7800, rubia.getCantidadActual(), 0.0001);

	}

}
