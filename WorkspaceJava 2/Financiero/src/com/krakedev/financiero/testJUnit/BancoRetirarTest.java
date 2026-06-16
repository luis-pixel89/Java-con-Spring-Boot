package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

@DisplayName("Pruebas unitarias - Banco.retirar")
public class BancoRetirarTest {
	private Banco banco;
	private Cuenta cuenta;

	@BeforeEach
	void setUp() {
		banco = new Banco();
		Cliente cliente = new Cliente("1700000001", "Ana", "Torres");
		cuenta = banco.crearCuenta(cliente);
		banco.depositar(1000.0, cuenta); // saldo inicial: 1000
	}

	@Test
	@DisplayName("Retirar monto válido retorna true")
	void testRetirarMontoValidoRetornaTrue() {
		boolean resultado = banco.retirar(500.0, cuenta);
		assertTrue(resultado);
	}

	@Test
	@DisplayName("Retirar monto válido descuenta el saldo correctamente")
	void testRetirarDescontaSaldo() {
		banco.retirar(400.0, cuenta);
		assertEquals(600.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Retirar el saldo exacto retorna true")
	void testRetirarSaldoExactoRetornaTrue() {
		boolean resultado = banco.retirar(1000.0, cuenta);
		assertTrue(resultado);
	}

	@Test
	@DisplayName("Retirar el saldo exacto deja la cuenta en cero")
	void testRetirarSaldoExactoDejaCuentaEnCero() {
		banco.retirar(1000.0, cuenta);
		assertEquals(0.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Retirar monto mayor al saldo retorna false")
	void testRetirarMontoMayorAlSaldoRetornaFalse() {
		boolean resultado = banco.retirar(1500.0, cuenta);
		assertFalse(resultado);
	}

	@Test
	@DisplayName("Retirar monto mayor al saldo no modifica el saldo")
	void testRetirarMontoMayorAlSaldoNoModificaSaldo() {
		banco.retirar(1500.0, cuenta);
		assertEquals(1000.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Retirar monto cero retorna false")
	void testRetirarMontoCeroRetornaFalse() {
		boolean resultado = banco.retirar(0, cuenta);
		assertFalse(resultado);
	}

	@Test
	@DisplayName("Retirar monto cero no modifica el saldo")
	void testRetirarMontoCeroNoModificaSaldo() {
		banco.retirar(0, cuenta);
		assertEquals(1000.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Retirar monto negativo retorna false")
	void testRetirarMontoNegativoRetornaFalse() {
		boolean resultado = banco.retirar(-200.0, cuenta);
		assertFalse(resultado);
	}

	@Test
	@DisplayName("Retirar monto negativo no modifica el saldo")
	void testRetirarMontoNegativoNoModificaSaldo() {
		banco.retirar(-200.0, cuenta);
		assertEquals(1000.0, cuenta.getSaldoActual());
	}
}
