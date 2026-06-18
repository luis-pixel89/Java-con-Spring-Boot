package com.krakedev.retoSpringBoot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.retoSpringBoot.entity.Producto;

@Service
public class ProductoService {

    // ✅ Almacenamiento en memoria — reemplaza la base de datos
    private List<Producto> productos = new ArrayList<>();

    // ── CREATE ──────────────────────────────────────────────
    public Producto crearProducto(Producto producto) {
        // Verificar que no exista un producto con el mismo código
        boolean existe = productos.stream()
                .anyMatch(p -> p.getCodigo().equals(producto.getCodigo()));

        if (existe) {
            throw new RuntimeException("Ya existe un producto con el código: " + producto.getCodigo());
        }

        productos.add(producto);
        return producto;
    }

    // ── READ ALL ─────────────────────────────────────────────
    public List<Producto> listarProductos() {
        return productos;
    }

    // ── READ BY ID ───────────────────────────────────────────
    public Producto buscarPorCodigo(String codigo) {
        Optional<Producto> resultado = productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();

        return resultado.orElseThrow(() ->
            new RuntimeException("Producto no encontrado con código: " + codigo)
        );
    }

    // ── UPDATE ───────────────────────────────────────────────
    public Producto actualizarProducto(String codigo, Producto productoActualizado) {
        Producto existente = buscarPorCodigo(codigo); // valida que exista
        existente.setNombre(productoActualizado.getNombre());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        return existente; // el objeto en el ArrayList ya quedó modificado
    }

    // ── DELETE ───────────────────────────────────────────────
    public void eliminarProducto(String codigo) {
        Producto existente = buscarPorCodigo(codigo); // valida que exista
        productos.remove(existente);
    }
}