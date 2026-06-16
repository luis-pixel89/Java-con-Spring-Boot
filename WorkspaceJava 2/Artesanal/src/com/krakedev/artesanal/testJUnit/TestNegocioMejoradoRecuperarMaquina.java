package com.krakedev.artesanal.testJUnit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejoradoRecuperarMaquina {
	@Test
	public void testRecuperarMaquina_DebeRetornarMaquinaExistente() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Tropical", "Jugo", 0.22);

		String codigo = negocio.getMaquinas().get(0).getCodigo();

		Maquina maquina = negocio.recuperarMaquina(codigo);

		assertNotNull(maquina);
		assertEquals(codigo, maquina.getCodigo());
	}

	@Test
	public void testRecuperarMaquina_DebeRetornarNullCuandoNoExiste() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.agregarMaquina("Cola", "Negra", 0.25);

		Maquina maquina = negocio.recuperarMaquina("M-999");

		assertNull(maquina);
	}

	@Test
	public void testRecuperarMaquina_ConListaVaciaDebeRetornarNull() {

		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = negocio.recuperarMaquina("M-1");

		assertNull(maquina);
	}
}
