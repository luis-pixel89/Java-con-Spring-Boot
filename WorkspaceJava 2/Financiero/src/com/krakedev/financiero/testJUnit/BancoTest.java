package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

@DisplayName("Pruebas unitarias - Banco")
public class BancoTest {
	private Banco banco;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
 
    @BeforeEach
    void setUp() {
        banco = new Banco();
        cliente1 = new Cliente("1700000001", "Ana", "Torres");
        cliente2 = new Cliente("1700000002", "Luis", "Gómez");
        cliente3 = new Cliente("1700000003", "María", "López");
    }
 
    // -------------------------------------------------------
    // 1. Código inicial
    // -------------------------------------------------------
 
    @Test
    @DisplayName("El banco inicia con ultimoCodigo = 1000")
    void testCodigoInicialEsMil() {
        assertEquals(1000, banco.getUltimoCodigo());
    }
 
    // -------------------------------------------------------
    // 2. Primera cuenta
    // -------------------------------------------------------
 
    @Test
    @DisplayName("La primera cuenta creada tiene ID '1000'")
    void testPrimeraCuentaTieneId1000() {
        Cuenta cuenta = banco.crearCuenta(cliente1);
        assertEquals("1000", cuenta.getId());
    }
 
    @Test
    @DisplayName("Después de crear una cuenta, ultimoCodigo es 1001")
    void testUltimoCodigoIncrementaTrasCrearCuenta() {
        banco.crearCuenta(cliente1);
        assertEquals(1001, banco.getUltimoCodigo());
    }
 
    // -------------------------------------------------------
    // 3. Códigos consecutivos
    // -------------------------------------------------------
 
    @Test
    @DisplayName("Tres cuentas creadas tienen IDs consecutivos: 1000, 1001, 1002")
    void testCodigosConsecutivos() {
        Cuenta c1 = banco.crearCuenta(cliente1);
        Cuenta c2 = banco.crearCuenta(cliente2);
        Cuenta c3 = banco.crearCuenta(cliente3);
 
        assertEquals("1000", c1.getId());
        assertEquals("1001", c2.getId());
        assertEquals("1002", c3.getId());
    }
 
    @Test
    @DisplayName("ultimoCodigo es 1003 después de crear tres cuentas")
    void testUltimoCodigoDespuesTresCuentas() {
        banco.crearCuenta(cliente1);
        banco.crearCuenta(cliente2);
        banco.crearCuenta(cliente3);
        assertEquals(1003, banco.getUltimoCodigo());
    }
 
    // -------------------------------------------------------
    // 4. Tipo de cuenta
    // -------------------------------------------------------
 
    @Test
    @DisplayName("La cuenta creada es de tipo Ahorros ('A')")
    void testTipoCuentaEsAhorros() {
        Cuenta cuenta = banco.crearCuenta(cliente1);
        assertEquals("A", cuenta.getTipo());
    }
 
    // -------------------------------------------------------
    // 5. Saldo inicial
    // -------------------------------------------------------
 
    @Test
    @DisplayName("La cuenta creada inicia con saldo 0")
    void testSaldoInicialEsCero() {
        Cuenta cuenta = banco.crearCuenta(cliente1);
        assertEquals(0.0, cuenta.getSaldoActual());
    }
 
    // -------------------------------------------------------
    // 6. Propietario asignado
    // -------------------------------------------------------
 
    @Test
    @DisplayName("La cuenta tiene asignado el cliente correcto")
    void testPropietarioCorrecto() {
        Cuenta cuenta = banco.crearCuenta(cliente1);
        assertNotNull(cuenta.getPropietario());
        assertEquals("1700000001", cuenta.getPropietario().getCedula());
        assertEquals("Ana", cuenta.getPropietario().getNombre());
        assertEquals("Torres", cuenta.getPropietario().getApellido());
    }
 
    @Test
    @DisplayName("Cada cuenta tiene su propio propietario")
    void testCadaCuentaTienePropioPropietario() {
        Cuenta c1 = banco.crearCuenta(cliente1);
        Cuenta c2 = banco.crearCuenta(cliente2);
 
        assertEquals("1700000001", c1.getPropietario().getCedula());
        assertEquals("1700000002", c2.getPropietario().getCedula());
    }
 
    // -------------------------------------------------------
    // 7. Cuenta no nula
    // -------------------------------------------------------
 
    @Test
    @DisplayName("crearCuenta no retorna null")
    void testCuentaNoEsNull() {
        Cuenta cuenta = banco.crearCuenta(cliente1);
        assertNotNull(cuenta);
    }
 
    // -------------------------------------------------------
    // 8. IDs son Strings numéricos distintos
    // -------------------------------------------------------
 
    @Test
    @DisplayName("Los IDs de dos cuentas consecutivas son distintos")
    void testIDsDistintos() {
        Cuenta c1 = banco.crearCuenta(cliente1);
        Cuenta c2 = banco.crearCuenta(cliente2);
        assertNotEquals(c1.getId(), c2.getId());
    }
}
