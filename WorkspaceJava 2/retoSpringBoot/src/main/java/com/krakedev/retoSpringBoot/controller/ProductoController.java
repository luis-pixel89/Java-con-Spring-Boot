package com.krakedev.retoSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.krakedev.retoSpringBoot.entity.Producto;
import com.krakedev.retoSpringBoot.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // POST /productos
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevo = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // GET /productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // GET /productos/{codigo}
    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(productoService.buscarPorCodigo(codigo));
    }

    // PUT /productos/{codigo}
    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable String codigo,
            @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizarProducto(codigo, producto));
    }

    // DELETE /productos/{codigo}
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        productoService.eliminarProducto(codigo);
        return ResponseEntity.noContent().build();
    }
}
