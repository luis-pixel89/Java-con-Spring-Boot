package com.krakedev.artesanal.evaluacion;

import java.util.ArrayList;

public class Curso {
	private String id;
	private ArrayList<Estudiante> estudiantes = new ArrayList<>();

	public Curso(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public void agregarEstudiante(Estudiante estudiante) {
		estudiantes.add(estudiante);
	}

	public Estudiante buscarPorCedula(String cedula) {
		for (int i = 0; i < estudiantes.size(); i++) {
			Estudiante est = estudiantes.get(i);
			if (est.getCedula().equals(cedula)) {
				return est;
			}
		}

		return null;
	}
	
	

}
