package com.krakedev.herencia;

public class Hija extends Padre {

	public Hija(int virtudes, int defectos) {
		super(virtudes, defectos);
	}

	public void escucharBadBunny() {
		System.out.println("Escuchando esta musica horrible");
	}

//	@Override
//	public String toString() {
//		return "Defecto: "+getDefectos()+" Virtudes: "+getVirtudes();
//	}

}
