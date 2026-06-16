package com.krakedev.taller.taller.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.taller.taller.entidades.Mecanico;
import com.krakedev.taller.taller.services.MecanicoService;

@RestController
@RequestMapping("/api/mecanicos")
public class MecanicoController {

    private final MecanicoService service;

    public MecanicoController(MecanicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Mecanico mecanico) {

        try {

            Mecanico nuevo = service.guardar(mecanico);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevo);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar mecánico");
        }
    }

    @GetMapping
    public ResponseEntity<List<Mecanico>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {

        try {

            Mecanico mecanico = service.buscar(id);

            if (mecanico == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecánico no encontrado");
            }

            return ResponseEntity.ok(mecanico);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar mecánico");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable int id,
            @RequestBody Mecanico mecanico) {

        try {

            Mecanico actualizado = service.actualizar(id, mecanico);

            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecánico no encontrado");
            }

            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar mecánico");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {

        try {

            boolean eliminado = service.eliminar(id);

            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Mecánico no encontrado");
            }

            return ResponseEntity.ok("Mecánico eliminado correctamente");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar mecánico");
        }
    }
}