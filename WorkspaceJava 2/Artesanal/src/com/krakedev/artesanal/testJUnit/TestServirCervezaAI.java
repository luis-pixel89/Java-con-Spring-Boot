package com.krakedev.artesanal.testJUnit;

import com.krakedev.artesanal.Maquina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestServirCervezaAI {

    static final double TOLERANCIA = 0.001;

    // ─────────────────────────────────────────────
    // CASOS: Servir exitosamente (hay suficiente cerveza)
    // ─────────────────────────────────────────────

    @Test
    void testServirCerveza_retornaValorCorrecto_constructorConCapacidad() {
        // Valida que el valor a pagar es cantidad * precioPorMl
        // Usa el constructor de 4 parámetros (capacidad personalizada)
        Maquina maquina = new Maquina("IPA", "Cerveza amarga", 2.5, 5000, "1");
        maquina.llenarMaquina(); // llena hasta capacidadMaxima - 200 = 4800 ml

        double valorAPagar = maquina.servirCerveza(500);

        assertEquals(500 * 2.5, valorAPagar, TOLERANCIA);
    }

    @Test
    void testServirCerveza_retornaValorCorrecto_constructorSinCapacidad() {
        // Valida que el valor a pagar es correcto usando el constructor de 3 parámetros
        // (capacidad por defecto = 10000, llenada = 9900 ml)
        Maquina maquina = new Maquina("Stout", "Cerveza oscura", 3.0, "1");
        maquina.llenarMaquina(); // llena hasta 9900 ml

        double valorAPagar = maquina.servirCerveza(200);

        assertEquals(200 * 3.0, valorAPagar, TOLERANCIA);
    }

    @Test
    void testServirCerveza_restaCantidadActualCorrectamente() {
        // Valida que la cantidad actual disminuye exactamente en lo servido
        Maquina maquina = new Maquina("Lager", "Cerveza suave", 1.5, 3000, "1");
        maquina.llenarMaquina(); // cantidadActual = 2800 ml

        maquina.servirCerveza(400);

        assertEquals(2800 - 400, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCerveza_cantidadExactaDisponible_sirveCorrectamente() {
        // Valida el caso borde: pedir exactamente la cantidad disponible
        Maquina maquina = new Maquina("Amber", "Cerveza roja", 2.0, 1000, "1");
        maquina.llenarMaquina(); // cantidadActual = 800 ml

        double valorAPagar = maquina.servirCerveza(800);

        assertEquals(800 * 2.0, valorAPagar, TOLERANCIA);
    }

    @Test
    void testServirCerveza_cantidadActualEsCeroTrasServir_cantidadExacta() {
        // Valida que la cantidad actual queda en 0 al servir todo lo disponible
        Maquina maquina = new Maquina("Amber", "Cerveza roja", 2.0, 1000, "1");
        maquina.llenarMaquina(); // cantidadActual = 800 ml

        maquina.servirCerveza(800);

        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    // ─────────────────────────────────────────────
    // CASOS: No hay suficiente cerveza
    // ─────────────────────────────────────────────

    @Test
    void testServirCerveza_sinCerveza_retornaCero() {
        // Valida que si la maquina está vacía, retorna 0
        Maquina maquina = new Maquina("Porter", "Cerveza tostada", 2.0, 2000, "1");
        // NO se llena → cantidadActual = 0

        double valorAPagar = maquina.servirCerveza(300);

        assertEquals(0, valorAPagar, TOLERANCIA);
    }

    @Test
    void testServirCerveza_sinCerveza_noCambiaCantidadActual() {
        // Valida que si no se puede servir, la cantidad actual no se modifica
        Maquina maquina = new Maquina("Porter", "Cerveza tostada", 2.0, 2000, "1");
        // NO se llena → cantidadActual = 0

        maquina.servirCerveza(300);

        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCerveza_pedirMasDeLoDisponible_retornaCero() {
        // Valida que pedir más de lo disponible retorna 0
        Maquina maquina = new Maquina("Weizen", "Cerveza de trigo", 1.8, 1000, "1");
        maquina.llenarMaquina(); // cantidadActual = 900 ml

        double valorAPagar = maquina.servirCerveza(1000); // pide más de 900

        assertEquals(0, valorAPagar, TOLERANCIA);
    }

    @Test
    void testServirCerveza_pedirMasDeLoDisponible_noCambiaCantidadActual() {
        // Valida que al no poder servir, la cantidad actual se mantiene intacta
        Maquina maquina = new Maquina("Weizen", "Cerveza de trigo", 1.8, 1000, "1");
        maquina.llenarMaquina(); // cantidadActual = 800 ml

        maquina.servirCerveza(1000); // pide más de 900

        assertEquals(800, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCerveza_pedirUnMililitroMasDelDisponible_retornaCero() {
        // Valida el caso borde: pedir exactamente 1 ml más de lo disponible
        Maquina maquina = new Maquina("IPA", "Cerveza amarga", 2.5, 1000, "1");
        maquina.llenarMaquina(); // cantidadActual = 900 ml

        double valorAPagar = maquina.servirCerveza(901); // 1 ml más de lo disponible

        assertEquals(0, valorAPagar, TOLERANCIA);
    }
}