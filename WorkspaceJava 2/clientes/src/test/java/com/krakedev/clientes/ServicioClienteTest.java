package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {
	private ServicioCliente servicio;

	@BeforeEach
	public void inicializar() {
		servicio = new ServicioCliente();

		Cliente c1 = new Cliente("0101", "Luis", "Perez","");
		Cliente c2 = new Cliente("0202", "Maria", "Lopez","");

		servicio.crear(c1);
		servicio.crear(c2);
	}

	// =========================================
	// buscarPorCedula
	// =========================================

	@Test
	public void testBuscarPorCedulaExistente() {
		Cliente cliente = servicio.buscarPorCedula("0101");

		assertNotNull(cliente);
		assertEquals("Luis", cliente.getNombre());
		assertEquals("Perez", cliente.getApellido());
	}

	@Test
	public void testBuscarPorCedulaNoExistente() {
		Cliente cliente = servicio.buscarPorCedula("9999");

		assertNull(cliente);
	}

	// =========================================
	// crear
	// =========================================

	@Test
	public void testCrearClienteNuevo() {
		Cliente nuevo = new Cliente("0303", "Carlos", "Mena","");

		Cliente resultado = servicio.crear(nuevo);

		assertNotNull(resultado);

		Cliente buscado = servicio.buscarPorCedula("0303");
		assertNotNull(buscado);

		assertEquals("Carlos", buscado.getNombre());
	}

	@Test
	public void testCrearClienteDuplicado() {
		Cliente duplicado = new Cliente("0101", "Otro", "Nombre","");

		Cliente resultado = servicio.crear(duplicado);

		assertNull(resultado);

		List<Cliente> clientes = servicio.listar();
		assertEquals(2, clientes.size());
	}

	// =========================================
	// listar
	// =========================================

	@Test
	public void testListarClientes() {
		List<Cliente> clientes = servicio.listar();

		assertEquals(2, clientes.size());
	}

	// =========================================
	// actualizar
	// =========================================

	@Test
	public void testActualizarClienteExistente() {
		Cliente actualizado = new Cliente();
		actualizado.setNombre("Luis Actualizado");
		actualizado.setApellido("Perez Actualizado");

		Cliente resultado = servicio.actualizar("0101", actualizado);

		assertNotNull(resultado);

		Cliente cliente = servicio.buscarPorCedula("0101");

		assertEquals("Luis Actualizado", cliente.getNombre());
		assertEquals("Perez Actualizado", cliente.getApellido());
	}

	@Test
	public void testActualizarClienteNoExistente() {
		Cliente actualizado = new Cliente();
		actualizado.setNombre("Nuevo");
		actualizado.setApellido("Apellido");

		Cliente resultado = servicio.actualizar("9999", actualizado);

		assertNull(resultado);
	}

	// =========================================
	// eliminar
	// =========================================

	@Test
	public void testEliminarClienteExistente() {
		boolean eliminado = servicio.eliminar("0101");

		assertTrue(eliminado);

		Cliente cliente = servicio.buscarPorCedula("0101");
		assertNull(cliente);
	}

	@Test
	public void testEliminarClienteNoExistente() {
		boolean eliminado = servicio.eliminar("9999");

		assertFalse(eliminado);
	}
}
