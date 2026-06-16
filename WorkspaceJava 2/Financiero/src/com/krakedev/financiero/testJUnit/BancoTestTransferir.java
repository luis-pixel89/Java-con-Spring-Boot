package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class BancoTestTransferir {
	private Banco banco;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;

	@BeforeEach
	public void inicializar() {

		banco = new Banco();

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();

		cuentaOrigen = banco.crearCuenta(cliente1);
		cuentaDestino = banco.crearCuenta(cliente2);

		banco.depositar(500, cuentaOrigen);
	}

	@Test
	public void testTransferenciaExitosa() {

		boolean resultado = banco.transferir(200, cuentaOrigen, cuentaDestino);

		assertTrue(resultado);
		assertEquals(300, cuentaOrigen.getSaldoActual());
		assertEquals(200, cuentaDestino.getSaldoActual());
	}

	@Test
	public void testTransferenciaSaldoInsuficiente() {

		boolean resultado = banco.transferir(1000, cuentaOrigen, cuentaDestino);

		assertFalse(resultado);
		assertEquals(500, cuentaOrigen.getSaldoActual());
		assertEquals(0, cuentaDestino.getSaldoActual());
	}

	@Test
	public void testTransferenciaMontoNegativo() {

		boolean resultado = banco.transferir(-50, cuentaOrigen, cuentaDestino);

		assertFalse(resultado);
		assertEquals(500, cuentaOrigen.getSaldoActual());
		assertEquals(0, cuentaDestino.getSaldoActual());
	}

	@Test
	public void testTransferenciaMontoCero() {

		boolean resultado = banco.transferir(0, cuentaOrigen, cuentaDestino);

		assertFalse(resultado);
		assertEquals(500, cuentaOrigen.getSaldoActual());
		assertEquals(0, cuentaDestino.getSaldoActual());
	}

	@Test
	public void testTransferenciaCuentaOrigenNull() {

		boolean resultado = banco.transferir(100, null, cuentaDestino);

		assertFalse(resultado);
	}

	@Test
	public void testTransferenciaCuentaDestinoNull() {

		boolean resultado = banco.transferir(100, cuentaOrigen, null);

		assertFalse(resultado);
	}
}
