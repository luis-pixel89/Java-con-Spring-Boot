package com.krakedev.juegos.test;

import java.util.ArrayList;

import com.krakedev.juegos.entidades.Jugador;
import com.krakedev.juegos.servicios.Juego21;

public class TestJuego21 {

	public static void main(String[] args) {
		Juego21 juego = new Juego21();

		Jugador j1 = new Jugador();
		j1.setNickname("Ana");
		Jugador j2 = new Jugador();
		j2.setNickname("Luis");
		Jugador j3 = new Jugador();
		j3.setNickname("Pedro");

		juego.agregarJugador(j1);
		juego.agregarJugador(j2);
		juego.agregarJugador(j3);

		juego.inicializar();

		// Primera prueba
		System.out.println("=== PRIMERA PRUEBA ===");
		ArrayList<Jugador> ganadores = juego.jugar();
		System.out.println("Ganadores: " + ganadores.size());
		for (Jugador g : ganadores) {
			g.imprimir();
		}

		// Segunda prueba
		System.out.println("\n=== SEGUNDA PRUEBA (10 iteraciones) ===");
		for (int i = 0; i < 10; i++) {
			juego.inicializar();
			juego.reiniciarJugadores();
			System.out.println("--- Partida " + (i + 1) + " ---");
			ganadores = juego.jugar();
			System.out.println("Ganadores: " + ganadores.size());
			for (Jugador g : ganadores) {
				g.imprimir();
			}
		}

	}

}
