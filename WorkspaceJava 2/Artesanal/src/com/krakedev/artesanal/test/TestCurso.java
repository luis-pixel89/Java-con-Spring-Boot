package com.krakedev.artesanal.test;

import com.krakedev.artesanal.evaluacion.Curso;
import com.krakedev.artesanal.evaluacion.Estudiante;

public class TestCurso {
	public static void main(String[] args) {
		Curso curso = new Curso("1");

		curso.agregarEstudiante(new Estudiante("1715893101", "Luis", "Suarez"));
		curso.agregarEstudiante(new Estudiante("1715893102", "Juan", "Perez"));
		curso.agregarEstudiante(new Estudiante("1715893103", "Joselyn", "Morales"));

		Estudiante encontrado = curso.buscarPorCedula("1715893101");
		System.out.println("Nombre: " + encontrado.getNombre());
		
		Estudiante encontrado1 = curso.buscarPorCedula("1715893104");
		System.out.println("Nombre: " + encontrado1.getNombre());
	}
}
