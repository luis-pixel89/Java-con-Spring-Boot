package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejoradoTotalConsumido {
	@Test
	public void testConsultarValorVendido_ListaVaciaDebeRetornarCero() {

		NegocioMejorado negocio = new NegocioMejorado();

		double total = negocio.consultarValorVendido();

		assertEquals(0, total);
	}

	@Test
	public void testConsultarValorVendido_UnCliente() {

		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = new Cliente("Luis", "1234567890");

		cliente.setTotalConsumido(250);

		negocio.getClientes().add(cliente);

		double total = negocio.consultarValorVendido();

		assertEquals(250, total);
	}

	@Test
	public void testConsultarValorVendido_VariosClientes() {

		NegocioMejorado negocio = new NegocioMejorado();

		Cliente c1 = new Cliente("Luis", "111");

		Cliente c2 = new Cliente("Carlos", "222");

		Cliente c3 = new Cliente("Pedro", "333");

		c1.setTotalConsumido(100);
		c2.setTotalConsumido(200);
		c3.setTotalConsumido(300);

		negocio.getClientes().add(c1);
		negocio.getClientes().add(c2);
		negocio.getClientes().add(c3);

		double total = negocio.consultarValorVendido();

		assertEquals(600, total);
	}

	@Test
	public void testConsultarValorVendido_ValoresDecimales() {

		NegocioMejorado negocio = new NegocioMejorado();

		Cliente c1 = new Cliente("Ana", "444");

		Cliente c2 = new Cliente("Maria", "555");

		c1.setTotalConsumido(125.5);
		c2.setTotalConsumido(74.5);

		negocio.getClientes().add(c1);
		negocio.getClientes().add(c2);

		double total = negocio.consultarValorVendido();

		assertEquals(200.0, total);
	}
}
