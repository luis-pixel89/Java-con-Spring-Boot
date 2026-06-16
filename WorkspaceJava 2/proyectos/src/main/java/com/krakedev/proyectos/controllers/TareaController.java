package com.krakedev.proyectos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.services.TareaService;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

	private final TareaService service;

	public TareaController(TareaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Tarea tarea) {
		try {
			Tarea nueva = service.guardar(tarea);

			return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el proyecto");
		}
	}

	@GetMapping
	public ResponseEntity<List<Tarea>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id) {
		try {
			Tarea tarea = service.buscar(id);

			if (tarea == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encotrada");
			}

			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la tarea");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Tarea tarea) {
		try {
			Tarea actualizada = service.actualizar(id, tarea);

			if (actualizada == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
			}

			return ResponseEntity.ok(actualizada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la tarea");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		try {
			boolean eliminado = service.eliminar(id);

			if (!eliminado) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
			}

			return ResponseEntity.ok("Tarea eliminada correctamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la tarea");
		}
	}

}
