package com.krakedev.taller.taller.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.taller.taller.entidades.Vehiculo;
import com.krakedev.taller.taller.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService service;

    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo nuevo = service.guardar(vehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar vehículo");
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscar(@PathVariable String placa) {

        try {
            Vehiculo vehiculo = service.buscar(placa);

            if (vehiculo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehículo no encontrado");
            }

            return ResponseEntity.ok(vehiculo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar vehículo");
        }
    }

    @PutMapping("/{placa}")
    public ResponseEntity<?> actualizar(
            @PathVariable String placa,
            @RequestBody Vehiculo vehiculo) {

        try {

            Vehiculo actualizado = service.actualizar(placa, vehiculo);

            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehículo no encontrado");
            }

            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar vehículo");
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<?> eliminar(@PathVariable String placa) {

        try {

            boolean eliminado = service.eliminar(placa);

            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vehículo no encontrado");
            }

            return ResponseEntity.ok("Vehículo eliminado correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar vehículo");
        }
    }
}