package com.krakedev.alien.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.alien.Alien;

public class AlientTest {

    // ── Atributos ────────────────────────────────────────────────────

    @Test
    public void testColorAsignadoCorrectamente() {
        Alien alien = new Alien(20, "Verde");
        assertEquals("Verde", alien.getColor());
    }

    @Test
    public void testTamanioAsignadoCorrectamente() {
        Alien alien = new Alien(20, "Azul");
        assertEquals(20, alien.getTamanio());
    }

    // ── Restricciones de tamaño ──────────────────────────────────────

    @Test
    public void testTamanioMenorAlMinimoSeAjustaA5() {
        Alien alien = new Alien(2, "Rojo");
        assertEquals(5, alien.getTamanio());
    }

    @Test
    public void testTamanioMayorAlMaximoSeAjustaA30() {
        Alien alien = new Alien(50, "Rojo");
        assertEquals(30, alien.getTamanio());
    }

    @Test
    public void testTamanioEnElLimiteInferior() {
        Alien alien = new Alien(5, "Gris");
        assertEquals(5, alien.getTamanio());
    }

    @Test
    public void testTamanioEnElLimiteSuperior() {
        Alien alien = new Alien(30, "Gris");
        assertEquals(30, alien.getTamanio());
    }

    @Test
    public void testTamanioNegativoSeAjustaAlMinimo() {
        Alien alien = new Alien(-10, "Negro");
        assertEquals(5, alien.getTamanio());
    }

    // ── Cálculo de precios ───────────────────────────────────────────

    @Test
    public void testPrecioCuerpoEsEl20PorCientoDelTamanio() {
        Alien alien = new Alien(20, "Verde");
        assertEquals(4.0, alien.getPrecioCuerpo(), 0.001);
    }

    @Test
    public void testPrecioExtremidadEsEl10PorCientoDelTamanio() {
        Alien alien = new Alien(20, "Verde");
        assertEquals(2.0, alien.getPrecioExtremidad(), 0.001);
    }

    @Test
    public void testPrecioOjoEsEl5PorCientoDelTamanio() {
        Alien alien = new Alien(20, "Verde");
        assertEquals(1.0, alien.getPrecioOjo(), 0.001);
    }

    @Test
    public void testPreciosConTamanioAjustadoPorMinimo() {
        // tamaño 2 → se ajusta a 5
        Alien alien = new Alien(2, "Azul");
        assertEquals(1.0,  alien.getPrecioCuerpo(),     0.001); // 5 * 0.20
        assertEquals(0.5,  alien.getPrecioExtremidad(), 0.001); // 5 * 0.10
        assertEquals(0.25, alien.getPrecioOjo(),        0.001); // 5 * 0.05
    }

    @Test
    public void testPreciosConTamanioAjustadoPorMaximo() {
        // tamaño 50 → se ajusta a 30
        Alien alien = new Alien(50, "Rojo");
        assertEquals(6.0, alien.getPrecioCuerpo(),     0.001); // 30 * 0.20
        assertEquals(3.0, alien.getPrecioExtremidad(), 0.001); // 30 * 0.10
        assertEquals(1.5, alien.getPrecioOjo(),        0.001); // 30 * 0.05
    }
}
