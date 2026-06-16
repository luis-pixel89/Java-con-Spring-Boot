package com.krakedev.asistencias.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.asistencias.entidades.Asistencia;
import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.entidades.RegistroAsistencia;

@Service
public class ServicioAsistencia {
	private ArrayList<RegistroAsistencia> registros = new ArrayList<>();
	private final ServicioEstudiantes servicioEstudiantes;

	public ServicioAsistencia(ServicioEstudiantes servicioEstudiantes) {
		this.servicioEstudiantes = servicioEstudiantes;
	}

	public RegistroAsistencia registrarAsistencia(String cedula) {
		// Buscar estudiante por cedula, en servicioEstudiantes
		// si no existe el estudiante retorna null
		// crea la asistencia, con la fecha y hora actuales

		Estudiante existente = servicioEstudiantes.buscarPorCedula(cedula);

		if (existente == null) {
			return null; // estudiante no existe
		}

		Asistencia asistencia = new Asistencia(LocalDate.now(), LocalDateTime.now(), "P");

		// crea un registro asistencia, agrega a la lista, retorna el registro creado

		RegistroAsistencia registro = new RegistroAsistencia(existente, asistencia);

		registros.add(registro);
		
		return registro;

	}

	public ArrayList<Asistencia> consultarAsistencia(String cedula) {
		// retorna todas las asistencias del estudiante
		Estudiante existente = servicioEstudiantes.buscarPorCedula(cedula);
		
		if(existente==null) {
			return null;
		}
		
		ArrayList<Asistencia> asistenciasEstudiante=new ArrayList<>();
		for(RegistroAsistencia r: registros) {
			if(r.getEstudiante().getCedula().equals(cedula)) {
				asistenciasEstudiante.add(r.getAsistencia());
			}
		}
		
		return asistenciasEstudiante;

	}
}
