package com.krakedev.asistencias.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.services.ServicioEstudiantes;

@RestController

@RequestMapping("/estudiantes")
public class ControladorEstudiantes {
	private final ServicioEstudiantes servicioEstudiante;
	
	public ControladorEstudiantes(ServicioEstudiantes servicioEstudiante) {
        this.servicioEstudiante = servicioEstudiante;
    }

	@PostMapping
	public Estudiante agregar(@RequestBody Estudiante estudiante) {
		return servicioEstudiante.agregar(estudiante);
	}

	@GetMapping
	public List<Estudiante> listar() {
		return servicioEstudiante.listar();
	}

	@GetMapping("/{cedula}")
	public Estudiante buscar(@PathVariable String cedula) {
		return servicioEstudiante.buscarPorCedula(cedula);
	}

	@PutMapping("/{cedula}")
	public void actualizar(@PathVariable String cedula, @RequestBody Estudiante estudianteActualizado) {
		servicioEstudiante.actualizar(cedula, estudianteActualizado);
	}
	
	@DeleteMapping("/{cedula}")
	public void eliminar(@PathVariable String cedula) {
		servicioEstudiante.eliminar(cedula);
	}
}
