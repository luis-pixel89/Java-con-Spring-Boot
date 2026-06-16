package com.krakedev.juegos.servicios;

import java.util.ArrayList;

import com.krakedev.juegos.entidades.Carta;
import com.krakedev.juegos.entidades.Jugador;

public class Juego21 {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private Dealer dealer;

	public Dealer getDealer() {
		return dealer;
	}

	public void cargarValores() {
		for (Carta carta : dealer.getNaipe()) {
			String valor = carta.getValor();
			int valorJuego;

			if (valor.equals("A")) {
				valorJuego = 11;
			} else if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
				valorJuego = 10;
			} else {
				valorJuego = Integer.parseInt(valor);
			}
			carta.setValorJuego(valorJuego);
		}

	}

	public void inicializar() {
		dealer = new Dealer();
		cargarValores();
	}

	public void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
	}

	public void repartirCarta(Jugador jugador) {
		Carta carta = dealer.entregarCarta();
		jugador.recibirCarta(carta);
	}

	public void repartirRonda() {
		for (Jugador j : jugadores) {
			repartirCarta(j);
		}

		calcularTotal();
	}

	public void calcularTotal() {
		for (Jugador j : jugadores) {
			int total = 0;

			for (Carta carta : j.getCartas()) {
				total += carta.getValorJuego();
			}
			j.setPuntajeCartas(total);
		}
	}

	public ArrayList<Jugador> validarGanador() {
		ArrayList<Jugador> ganadores = new ArrayList<>();

		for (Jugador j : jugadores) {
			if (j.getPuntajeCartas() == 21) {
				ganadores.add(j);
			}
		}

		return ganadores;
	}

	public ArrayList<Jugador> jugar() {
		ArrayList<Jugador> ganadores = new ArrayList<>();
		
		for(int i=0; i<3; i++) {
			repartirRonda();
			ganadores=validarGanador();
			
			if(ganadores.size()>0) {
				break;
			}
		}
		
		return ganadores;
	}
	
	
	public void reiniciarJugadores() {
		for(Jugador j: jugadores) {
			j.reiniciar();
		}
	}
}
