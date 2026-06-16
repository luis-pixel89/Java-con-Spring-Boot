package com.krakedev.asistencias.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.asistencias.entidades.Asistencia;
import com.krakedev.asistencias.entidades.RegistroAsistencia;
import com.krakedev.asistencias.services.ServicioAsistencia;

@RestController

@RequestMapping("/asistencia")
public class ControladorAsistencia {
	private final ServicioAsistencia servicioAsistencia;
	
	public ControladorAsistencia(ServicioAsistencia servicioAsistencia) {
		this.servicioAsistencia=servicioAsistencia;
	}
	
	@PostMapping("/{cedula}")
	public RegistroAsistencia registrar(@PathVariable String cedula) {
	    RegistroAsistencia resultado = servicioAsistencia.registrarAsistencia(cedula);
	    return resultado;
	}
	
	@GetMapping("/{cedula}")
	public ArrayList<Asistencia>consultar(@PathVariable String cedula){
		return servicioAsistencia.consultarAsistencia(cedula);
	}
}
