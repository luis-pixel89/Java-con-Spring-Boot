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

@DisplayName("Pruebas unitarias - Banco.depositar")
public class BancoDepositarTest {
	private Banco banco;
	private Cuenta cuenta;

	@BeforeEach
	void setUp() {
		banco = new Banco();
		Cliente cliente = new Cliente("1700000001", "Ana", "Torres");
		cuenta = banco.crearCuenta(cliente);
	}

	@Test
	@DisplayName("Depositar monto válido retorna true")
	void testDepositarMontoValidoRetornaTrue() {
		boolean resultado = banco.depositar(500.0, cuenta);
		assertTrue(resultado);
	}

	@Test
	@DisplayName("Depositar monto válido actualiza el saldo correctamente")
	void testDepositarActualizaSaldo() {
		banco.depositar(500.0, cuenta);
		assertEquals(500.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Dos depósitos acumulan el saldo correctamente")
	void testDosDepositosAcumulanSaldo() {
		banco.depositar(300.0, cuenta);
		banco.depositar(200.0, cuenta);
		assertEquals(500.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Depositar monto cero retorna false")
	void testDepositarMontoCeroRetornaFalse() {
		boolean resultado = banco.depositar(0, cuenta);
		assertFalse(resultado);
	}

	@Test
	@DisplayName("Depositar monto cero no modifica el saldo")
	void testDepositarMontoCeroNoModificaSaldo() {
		banco.depositar(0, cuenta);
		assertEquals(0.0, cuenta.getSaldoActual());
	}

	@Test
	@DisplayName("Depositar monto negativo retorna false")
	void testDepositarMontoNegativoRetornaFalse() {
		boolean resultado = banco.depositar(-100.0, cuenta);
		assertFalse(resultado);
	}

	@Test
	@DisplayName("Depositar monto negativo no modifica el saldo")
	void testDepositarMontoNegativoNoModificaSaldo() {
		banco.depositar(-100.0, cuenta);
		assertEquals(0.0, cuenta.getSaldoActual());
	}
}
