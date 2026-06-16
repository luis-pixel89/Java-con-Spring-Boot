package com.krakedev.contacto.entidades.test.JUnit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.krakedev.contacto.entidades.Contacto;
import com.krakedev.contacto.entidades.Directorio;

public class DirectorioTest {
	@Test
	public void testBuscarContactoExistente() {

		// Se prueba buscar un contacto que sí existe en el directorio
		// Resultado esperado: debe retornar el contacto encontrado

		Directorio directorio = new Directorio();

		Contacto contacto = new Contacto();
		contacto.setNombre("Luis");
		contacto.setApellido("Perez");
		contacto.setEdad(25);
		contacto.setCelular("0999999999");
		contacto.setPeso(70.5);

		directorio.agregarContacto(contacto);

		Contacto encontrado = directorio.buscarContacto("0999999999");

		assertEquals("Luis", encontrado.getNombre());
	}

	@Test
	public void testBuscarContactoNoExistente() {

		// Se prueba buscar un contacto que no existe
		// Resultado esperado: debe retornar null

		Directorio directorio = new Directorio();

		Contacto encontrado = directorio.buscarContacto("0888888888");

		assertNull(encontrado);
	}

	@Test
	public void testEliminarContactoExistente() {

		// Se prueba eliminar un contacto existente
		// Resultado esperado: debe retornar true

		Directorio directorio = new Directorio();

		Contacto contacto = new Contacto();
		contacto.setNombre("Carlos");
		contacto.setApellido("Lopez");
		contacto.setEdad(30);
		contacto.setCelular("0777777777");
		contacto.setPeso(80.0);

		directorio.agregarContacto(contacto);

		boolean eliminado = directorio.eliminarContacto("0777777777");

		assertTrue(eliminado);
	}

	@Test
	public void testEliminarContactoNoExistente() {

		// Se prueba eliminar un contacto que no existe
		// Resultado esperado: debe retornar false

		Directorio directorio = new Directorio();

		boolean eliminado = directorio.eliminarContacto("0666666666");

		assertFalse(eliminado);
	}

	@Test
	public void testBuscarContactosCoincidenciaConResultados() {

		// Se prueba buscar contactos cuyos nombres comienzan con "Lu"
		// Resultado esperado: debe encontrar coincidencias

		Directorio directorio = new Directorio();

		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Luis");
		contacto1.setApellido("Perez");
		contacto1.setEdad(25);
		contacto1.setCelular("0111111111");
		contacto1.setPeso(70);

		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Lucia");
		contacto2.setApellido("Martinez");
		contacto2.setEdad(22);
		contacto2.setCelular("0222222222");
		contacto2.setPeso(55);

		Contacto contacto3 = new Contacto();
		contacto3.setNombre("Carlos");
		contacto3.setApellido("Gomez");
		contacto3.setEdad(28);
		contacto3.setCelular("0333333333");
		contacto3.setPeso(78);

		directorio.agregarContacto(contacto1);
		directorio.agregarContacto(contacto2);
		directorio.agregarContacto(contacto3);

		ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("Lu");

		assertEquals(2, encontrados.size());
	}

	@Test
	public void testBuscarContactosCoincidenciaSinResultados() {

		// Se prueba buscar contactos con una subcadena sin coincidencias
		// Resultado esperado: la lista debe estar vacía

		Directorio directorio = new Directorio();

		Contacto contacto = new Contacto();
		contacto.setNombre("Pedro");
		contacto.setApellido("Ramirez");
		contacto.setEdad(40);
		contacto.setCelular("0444444444");
		contacto.setPeso(82);

		directorio.agregarContacto(contacto);

		ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("Lu");

		assertEquals(0, encontrados.size());
	}

	@Test
	public void testBuscarContactosCoincidenciaVerificarCantidadResultados() {

		// Se prueba la cantidad exacta de coincidencias encontradas
		// Resultado esperado: debe retornar 3 contactos

		Directorio directorio = new Directorio();

		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Luis");
		contacto1.setCelular("100");

		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Lucia");
		contacto2.setCelular("200");

		Contacto contacto3 = new Contacto();
		contacto3.setNombre("Luisa");
		contacto3.setCelular("300");

		Contacto contacto4 = new Contacto();
		contacto4.setNombre("Pedro");
		contacto4.setCelular("400");

		directorio.agregarContacto(contacto1);
		directorio.agregarContacto(contacto2);
		directorio.agregarContacto(contacto3);
		directorio.agregarContacto(contacto4);

		ArrayList<Contacto> encontrados = directorio.buscarContactosCoincidencia("Lu");

		assertEquals(3, encontrados.size());
	}
}
