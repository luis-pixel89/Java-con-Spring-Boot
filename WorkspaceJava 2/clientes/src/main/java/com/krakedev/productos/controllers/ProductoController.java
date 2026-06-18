package com.krakedev.productos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.crear(producto);
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{codigo}")
    public Producto buscarPorCodigo(@PathVariable int codigo) {
        return productoService.buscarPorCodigo(codigo);
    }

    @PutMapping("/{codigo}")
    public Producto actualizar(@PathVariable int codigo,
                               @RequestBody Producto productoActualizado) {
        return productoService.actualizar(codigo, productoActualizado);
    }

    @DeleteMapping("/{codigo}")
    public boolean eliminar(@PathVariable int codigo) {
        return productoService.eliminar(codigo);
    }
}