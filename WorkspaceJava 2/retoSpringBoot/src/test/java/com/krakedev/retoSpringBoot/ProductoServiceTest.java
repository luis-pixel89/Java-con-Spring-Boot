package com.krakedev.retoSpringBoot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.retoSpringBoot.entity.Producto;
import com.krakedev.retoSpringBoot.service.ProductoService;

class ProductoServiceTest {

    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de CADA test → ArrayList siempre limpio
        productoService = new ProductoService();
    }

    // ════════════════════════════════════════════════════════
    // 1. REGISTRO CORRECTO DE UN OBJETO
    // ════════════════════════════════════════════════════════

    @Test
    void testRegistro_productoNuevo_exitoso() {
        // ARRANGE — preparar datos
        Producto producto = new Producto("P001", "Laptop Dell", 1500.0, 100);

        // ACT — ejecutar la acción
        Producto resultado = productoService.crearProducto(producto);

        // ASSERT — verificar resultados
        assertNotNull(resultado,               "El producto no debe ser null");
        assertEquals("P001",       resultado.getCodigo(),  "El código debe coincidir");
        assertEquals("Laptop Dell",resultado.getNombre(),  "El nombre debe coincidir");
        assertEquals(1500.0,       resultado.getPrecio(),  "El precio debe coincidir");
    }

    @Test
    void testRegistro_variosProductos_listaCreceCorrectamente() {
        // ARRANGE
        Producto p1 = new Producto("P001", "Laptop Dell", 1500.0, 10);
        Producto p2 = new Producto("P002", "Mouse Logitech", 25.0, 20);
        Producto p3 = new Producto("P003", "Teclado HP", 45.0, 30);

        // ACT
        productoService.crearProducto(p1);
        productoService.crearProducto(p2);
        productoService.crearProducto(p3);

        // ASSERT
        List<Producto> lista = productoService.listarProductos();
        assertEquals(3, lista.size(), "Deben existir exactamente 3 productos registrados");
    }

    @Test
    void testRegistro_codigoDuplicado_lanzaExcepcion() {
        // ARRANGE — registrar un producto primero
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 100));

        // ACT + ASSERT — intentar registrar con el mismo código
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            productoService.crearProducto(new Producto("P001", "Otro Producto", 99.0, 20))
        );

        assertTrue(
            ex.getMessage().contains("Ya existe un producto con el código: P001"),
            "El mensaje de error debe indicar el código duplicado"
        );
    }

    // ════════════════════════════════════════════════════════
    // 2. BÚSQUEDA POR IDENTIFICADOR
    // ════════════════════════════════════════════════════════

    @Test
    void testBusqueda_codigoExistente_retornaProductoCorrecto() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));
        productoService.crearProducto(new Producto("P002", "Mouse Logitech", 25.0, 30));

        // ACT — buscar uno específico entre varios
        Producto resultado = productoService.buscarPorCodigo("P002");

        // ASSERT
        assertNotNull(resultado,                  "El resultado no debe ser null");
        assertEquals("P002",           resultado.getCodigo(), "El código debe ser P002");
        assertEquals("Mouse Logitech", resultado.getNombre(), "El nombre debe coincidir");
        assertEquals(25.0,             resultado.getPrecio(), "El precio debe coincidir");
    }

    @Test
    void testBusqueda_retornaElProductoExacto_noOtro() {
        // ARRANGE — registrar dos productos similares
        productoService.crearProducto(new Producto("P010", "Monitor Samsung 24", 350.0, 20));
        productoService.crearProducto(new Producto("P011", "Monitor LG 27", 500.0, 20));

        // ACT
        Producto resultado = productoService.buscarPorCodigo("P010");

        // ASSERT — debe devolver exactamente P010, no P011
        assertEquals("P010",              resultado.getCodigo());
        assertEquals("Monitor Samsung 24",resultado.getNombre());
        assertNotEquals("P011",           resultado.getCodigo(), "No debe devolver otro producto");
    }

    // ════════════════════════════════════════════════════════
    // 3. ACTUALIZACIÓN DE DATOS
    // ════════════════════════════════════════════════════════

    @Test
    void testActualizacion_nombreYPrecio_seModificanCorrectamente() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 20));
        Producto datosActualizados = new Producto("P001", "Laptop Dell XPS", 2000.0, 20);

        // ACT
        Producto resultado = productoService.actualizarProducto("P001", datosActualizados);

        // ASSERT
        assertEquals("Laptop Dell XPS", resultado.getNombre(), "El nombre debe haberse actualizado");
        assertEquals(2000.0,            resultado.getPrecio(), "El precio debe haberse actualizado");
        assertEquals("P001",            resultado.getCodigo(), "El código NO debe cambiar");
    }

    @Test
    void testActualizacion_cambioSePersiste_enElArrayList() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));

        // ACT — actualizar y luego volver a buscar
        productoService.actualizarProducto("P001", new Producto("P001", "Laptop Dell XPS", 2000.0, 10));
        Producto desdeLista = productoService.buscarPorCodigo("P001");

        // ASSERT — el cambio debe reflejarse al buscarlo de nuevo
        assertEquals("Laptop Dell XPS", desdeLista.getNombre(), "El cambio debe persistir en la lista");
        assertEquals(2000.0,            desdeLista.getPrecio(), "El precio actualizado debe persistir");
    }

    @Test
    void testActualizacion_soloUnProducto_losDemasNoSeAfectan() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));
        productoService.crearProducto(new Producto("P002", "Mouse Logitech", 25.0, 10));

        // ACT — actualizar solo P001
        productoService.actualizarProducto("P001", new Producto("P001", "Laptop HP", 1800.0, 10));

        // ASSERT — P002 no debe haber cambiado
        Producto p2 = productoService.buscarPorCodigo("P002");
        assertEquals("Mouse Logitech", p2.getNombre(), "P002 no debe haberse modificado");
        assertEquals(25.0,             p2.getPrecio(), "El precio de P002 no debe cambiar");
    }

    // ════════════════════════════════════════════════════════
    // 4. ELIMINACIÓN DE UN OBJETO
    // ════════════════════════════════════════════════════════

    @Test
    void testEliminacion_productoExistente_seEliminaCorrectamente() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));

        // ACT
        productoService.eliminarProducto("P001");

        // ASSERT — la lista debe quedar vacía
        List<Producto> lista = productoService.listarProductos();
        assertEquals(0, lista.size(), "La lista debe estar vacía después de eliminar");
    }

    @Test
    void testEliminacion_soloEliminaElIndicado_noLosDemas() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));
        productoService.crearProducto(new Producto("P002", "Mouse Logitech", 25.0, 10));
        productoService.crearProducto(new Producto("P003", "Teclado HP", 45.0, 10));

        // ACT — eliminar solo P002
        productoService.eliminarProducto("P002");

        // ASSERT
        List<Producto> lista = productoService.listarProductos();
        assertEquals(2, lista.size(), "Deben quedar 2 productos");

        // P001 y P003 deben seguir existiendo
        assertDoesNotThrow(() -> productoService.buscarPorCodigo("P001"), "P001 debe seguir existiendo");
        assertDoesNotThrow(() -> productoService.buscarPorCodigo("P003"), "P003 debe seguir existiendo");
    }

    @Test
    void testEliminacion_productoYaNoAccesible_luegoDeBorrar() {
        // ARRANGE
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));

        // ACT
        productoService.eliminarProducto("P001");

        // ASSERT — intentar buscarlo debe lanzar excepción
        assertThrows(RuntimeException.class,
            () -> productoService.buscarPorCodigo("P001"),
            "Buscar un producto eliminado debe lanzar excepción"
        );
    }

    // ════════════════════════════════════════════════════════
    // 5. CASO CUANDO UN OBJETO NO EXISTE
    // ════════════════════════════════════════════════════════

    @Test
    void testBusqueda_codigoInexistente_lanzaExcepcion() {
        // ARRANGE — lista vacía, no se registra nada

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> productoService.buscarPorCodigo("NOEXISTE")
        );

        assertTrue(
            ex.getMessage().contains("Producto no encontrado con código: NOEXISTE"),
            "El mensaje debe indicar qué código no se encontró"
        );
    }

    @Test
    void testActualizacion_codigoInexistente_lanzaExcepcion() {
        // ARRANGE — lista vacía

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> productoService.actualizarProducto("NOEXISTE",
                      new Producto("NOEXISTE", "Fantasma", 0.0, 10))
        );

        assertTrue(
            ex.getMessage().contains("Producto no encontrado con código: NOEXISTE"),
            "Actualizar un producto inexistente debe lanzar excepción"
        );
    }

    @Test
    void testEliminacion_codigoInexistente_lanzaExcepcion() {
        // ARRANGE — lista vacía

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> productoService.eliminarProducto("NOEXISTE")
        );

        assertTrue(
            ex.getMessage().contains("Producto no encontrado con código: NOEXISTE"),
            "Eliminar un producto inexistente debe lanzar excepción"
        );
    }

    @Test
    void testBusqueda_listaConDatos_peroCodigoNoCoincide_lanzaExcepcion() {
        // ARRANGE — hay productos pero ninguno tiene el código buscado
        productoService.crearProducto(new Producto("P001", "Laptop Dell", 1500.0, 10));
        productoService.crearProducto(new Producto("P002", "Mouse Logitech", 25.0, 10));

        // ACT + ASSERT
        assertThrows(RuntimeException.class,
            () -> productoService.buscarPorCodigo("P999"),
            "Debe lanzar excepción aunque la lista no esté vacía"
        );
    }
}