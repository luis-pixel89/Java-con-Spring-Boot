package com.krakedev.taller.taller.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.taller.taller.entidades.Reparacion;
import com.krakedev.taller.taller.services.ReparacionService;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {

    private final ReparacionService service;

    public ReparacionController(ReparacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Reparacion reparacion) {

        try {

            Reparacion nueva = service.guardar(reparacion);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nueva);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar reparación");
        }
    }

    @GetMapping
    public ResponseEntity<List<Reparacion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {

        try {

            Reparacion reparacion = service.buscar(id);

            if (reparacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reparación no encontrada");
            }

            return ResponseEntity.ok(reparacion);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar reparación");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable int id,
            @RequestBody Reparacion reparacion) {

        try {

            Reparacion actualizada = service.actualizar(id, reparacion);

            if (actualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reparación no encontrada");
            }

            return ResponseEntity.ok(actualizada);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar reparación");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {

        try {

            boolean eliminado = service.eliminar(id);

            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reparación no encontrada");
            }

            return ResponseEntity.ok("Reparación eliminada correctamente");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar reparación");
        }
    }
}