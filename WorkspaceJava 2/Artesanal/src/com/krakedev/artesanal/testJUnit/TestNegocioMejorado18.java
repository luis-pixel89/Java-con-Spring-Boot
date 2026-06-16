package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejorado18 {
	@Test
	public void testConsumirCerveza_DebeActualizarTotalConsumidoDelCliente() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Luis", "1723456789");

		negocio.agregarMaquina("Pilsener", "Rubia", 0.25);

		Cliente cliente = negocio.buscarClientePorCedula("1723456789");

		Maquina maquina = negocio.getMaquinas().get(0);

		negocio.consumirCerveza(cliente.getCodigo(), maquina.getCodigo(), 300);

		assertEquals(300, cliente.getTotalConsumido());
	}

	@Test
	public void testConsumirCerveza_DebeDisminuirCapacidadDeLaMaquina() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Carlos", "1111111111");

		negocio.agregarMaquina("Club", "Negra", 0.30);

		Maquina maquina = negocio.getMaquinas().get(0);

		maquina.llenarMaquina();

		double capacidadInicial = maquina.getCantidadActual();

		Cliente cliente = negocio.buscarClientePorCedula("1111111111");

		negocio.consumirCerveza(cliente.getCodigo(), maquina.getCodigo(), 500);

		double capacidadFinal = maquina.getCantidadActual();

		assertEquals(capacidadInicial - 500, capacidadFinal);
	}

	@Test
	public void testConsumirCerveza_DebeMantenerValoresCorrectos() {

		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Pedro", "2222222222");

		negocio.agregarMaquina("Heineken", "Premium", 0.50);

		Cliente cliente = negocio.buscarClientePorCedula("2222222222");

		Maquina maquina = negocio.getMaquinas().get(0);

		maquina.llenarMaquina();

		negocio.consumirCerveza(cliente.getCodigo(), maquina.getCodigo(), 250);

		assertEquals(250, cliente.getTotalConsumido());

		assertTrue(maquina.getCantidadActual() >= 0);

		assertEquals("Heineken", maquina.getNombreCerveza());
	}

	@Test
	public void testRegistrarConsumo_DebeAcumularConsumo() {

		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = new Cliente("Luis", "1234567890");

		negocio.registrarConsumo(cliente, 200);

		negocio.registrarConsumo(cliente, 300);

		assertEquals(500, cliente.getTotalConsumido());
	}
}
