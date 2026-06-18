package com.krakedev.productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.services.ProductoService;

public class ProductoServiceTest {

    @Test
    void crearProductoExitoso() {
        // Se espera que el producto se agregue correctamente
        ProductoService service = new ProductoService();

        Producto producto = new Producto("Laptop", 1, 2500.0, 10);

        Producto resultado = service.crear(producto);

        assertEquals(producto, resultado);
        assertEquals(1, service.listar().size());
    }

    @Test
    void crearProductoDuplicado() {
        // Se espera null cuando ya existe un producto con el mismo código
        ProductoService service = new ProductoService();

        Producto producto1 = new Producto("Laptop", 1, 2500.0, 10);
        Producto producto2 = new Producto("Mouse", 1, 50.0, 20);

        service.crear(producto1);
        Producto resultado = service.crear(producto2);

        assertNull(resultado);
        assertEquals(1, service.listar().size());
    }

    @Test
    void buscarProductoExistente() {
        // Se espera encontrar el producto por su código
        ProductoService service = new ProductoService();

        Producto producto = new Producto("Laptop", 1, 2500.0, 10);
        service.crear(producto);

        Producto resultado = service.buscarPorCodigo(1);

        assertEquals(producto, resultado);
    }

    @Test
    void buscarProductoNoExistente() {
        // Se espera null cuando el producto no existe
        ProductoService service = new ProductoService();

        Producto resultado = service.buscarPorCodigo(999);

        assertNull(resultado);
    }

    @Test
    void listarProductos() {
        // Se espera obtener todos los productos almacenados
        ProductoService service = new ProductoService();

        service.crear(new Producto("Laptop", 1, 2500.0, 10));
        service.crear(new Producto("Mouse", 2, 50.0, 20));

        assertEquals(2, service.listar().size());
    }

    @Test
    void actualizarProductoExistente() {
        // Se espera actualizar nombre, precio y stock del producto existente
        ProductoService service = new ProductoService();

        Producto producto = new Producto("Laptop", 1, 2500.0, 10);
        service.crear(producto);

        Producto actualizado = new Producto("Laptop Gamer", 1, 3500.0, 5);

        Producto resultado = service.actualizar(1, actualizado);

        assertEquals("Laptop Gamer", resultado.getNombre());
        assertEquals(3500.0, resultado.getPrecio());
        assertEquals(5, resultado.getStock());
    }

    @Test
    void actualizarProductoNoExistente() {
        // Se espera null cuando el producto a actualizar no existe
        ProductoService service = new ProductoService();

        Producto actualizado = new Producto("Laptop Gamer", 1, 3500.0, 5);

        Producto resultado = service.actualizar(1, actualizado);

        assertNull(resultado);
    }

    @Test
    void eliminarProductoExistente() {
        // Se espera eliminar correctamente el producto existente
        ProductoService service = new ProductoService();

        service.crear(new Producto("Laptop", 1, 2500.0, 10));

        boolean resultado = service.eliminar(1);

        assertTrue(resultado);
        assertEquals(0, service.listar().size());
    }

    @Test
    void eliminarProductoNoExistente() {
        // Se espera false cuando el producto no existe
        ProductoService service = new ProductoService();

        boolean resultado = service.eliminar(999);

        assertFalse(resultado);
    }
}