package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejoradoAgregarMaquina {
	@Test
	public void testAgregarMaquina_DebeAgregarUnaMaquina() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Coca Cola", "Bebida gaseosa", 0.25);

		assertEquals(1, negocio.getMaquinas().size());
	}

	@Test
	public void testAgregarMaquina_DebeGuardarDatosCorrectamente() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Pepsi", "Bebida negra", 0.30);

		Maquina maquina = negocio.getMaquinas().get(0);

		assertEquals("Pepsi", maquina.getNombreCerveza());
		assertEquals("Bebida negra", maquina.getDescripcion());
		assertEquals(0.30, maquina.getPrecioPorMl());
	}

	@Test
	public void testAgregarMaquina_DebeGenerarCodigoAutomaticamente() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Fanta", "Naranja", 0.20);

		Maquina maquina = negocio.getMaquinas().get(0);

		assertNotNull(maquina.getCodigo());
		assertTrue(maquina.getCodigo().startsWith("M-"));
	}
}
