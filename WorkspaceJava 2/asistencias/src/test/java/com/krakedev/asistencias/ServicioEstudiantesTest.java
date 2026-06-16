package com.krakedev.asistencias;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.services.ServicioEstudiantes;

class ServicioEstudiantesTest {

    private ServicioEstudiantes servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioEstudiantes();
    }

    @Test
    void deberiaAgregarEstudianteNuevo() {
        Estudiante estudiante =
                new Estudiante("0101010101", "Luis", "Perez");

        Estudiante resultado = servicio.agregar(estudiante);

        assertNotNull(resultado);
        assertEquals("0101010101", resultado.getCedula());
        assertEquals(1, servicio.listar().size());
    }

    @Test
    void noDeberiaAgregarCedulaDuplicada() {

        Estudiante e1 =
                new Estudiante("0101010101", "Luis", "Perez");

        Estudiante e2 =
                new Estudiante("0101010101", "Juan", "Lopez");

        servicio.agregar(e1);

        Estudiante resultado = servicio.agregar(e2);

        assertNull(resultado);
        assertEquals(1, servicio.listar().size());
    }

    @Test
    void deberiaBuscarEstudianteExistente() {

        Estudiante estudiante =
                new Estudiante("0202020202", "Maria", "Vera");

        servicio.agregar(estudiante);

        Estudiante encontrado =
                servicio.buscarPorCedula("0202020202");

        assertNotNull(encontrado);
        assertEquals("Maria", encontrado.getNombre());
    }

    @Test
    void buscarCedulaInexistenteDebeRetornarNull() {

        Estudiante encontrado =
                servicio.buscarPorCedula("9999999999");

        assertNull(encontrado);
    }

    @Test
    void deberiaEliminarEstudianteExistente() {

        Estudiante estudiante =
                new Estudiante("0303030303", "Carlos", "Mora");

        servicio.agregar(estudiante);

        servicio.eliminar("0303030303");

        assertEquals(0, servicio.listar().size());
        assertNull(servicio.buscarPorCedula("0303030303"));
    }

    @Test
    void eliminarCedulaInexistenteNoDebeGenerarError() {

        Estudiante estudiante =
                new Estudiante("0404040404", "Pedro", "Diaz");

        servicio.agregar(estudiante);

        assertDoesNotThrow(() ->
                servicio.eliminar("9999999999"));

        assertEquals(1, servicio.listar().size());
    }

    @Test
    void deberiaActualizarEstudianteExistente() {

        Estudiante original =
                new Estudiante("0505050505", "Ana", "Lopez");

        servicio.agregar(original);

        Estudiante actualizado =
                new Estudiante("0606060606", "Ana Maria", "Torres");

        servicio.actualizar("0505050505", actualizado);

        Estudiante resultado =
                servicio.buscarPorCedula("0606060606");

        assertNotNull(resultado);
        assertEquals("Ana Maria", resultado.getNombre());
        assertEquals("Torres", resultado.getApellido());
        assertEquals("0606060606", resultado.getCedula());
    }

    @Test
    void actualizarCedulaInexistenteNoDebeModificarNada() {

        Estudiante estudiante =
                new Estudiante("0707070707", "Jorge", "Mendez");

        servicio.agregar(estudiante);

        Estudiante nuevo =
                new Estudiante("0808080808", "Mario", "Sanchez");

        servicio.actualizar("9999999999", nuevo);

        Estudiante resultado =
                servicio.buscarPorCedula("0707070707");

        assertNotNull(resultado);
        assertEquals("Jorge", resultado.getNombre());
        assertEquals("Mendez", resultado.getApellido());
    }

    @Test
    void listarDebeRetornarListaVaciaAlInicio() {

        ArrayList<Estudiante> estudiantes =
                servicio.listar();

        assertNotNull(estudiantes);
        assertTrue(estudiantes.isEmpty());
    }

    @Test
    void listarDebeRetornarTodosLosEstudiantes() {

        servicio.agregar(
                new Estudiante("111", "Luis", "Perez"));

        servicio.agregar(
                new Estudiante("222", "Maria", "Vera"));

        servicio.agregar(
                new Estudiante("333", "Juan", "Lopez"));

        ArrayList<Estudiante> estudiantes =
                servicio.listar();

        assertEquals(3, estudiantes.size());
    }

    @Test
    void despuesDeEliminarDebeDesaparecerDeLaBusqueda() {

        Estudiante estudiante =
                new Estudiante("1212121212", "Diego", "Ruiz");

        servicio.agregar(estudiante);

        servicio.eliminar("1212121212");

        Estudiante resultado =
                servicio.buscarPorCedula("1212121212");

        assertNull(resultado);
    }

    @Test
    void agregarVariosEstudiantesConCedulasDistintas() {

        Estudiante e1 =
                new Estudiante("1", "A", "A");

        Estudiante e2 =
                new Estudiante("2", "B", "B");

        Estudiante e3 =
                new Estudiante("3", "C", "C");

        assertNotNull(servicio.agregar(e1));
        assertNotNull(servicio.agregar(e2));
        assertNotNull(servicio.agregar(e3));

        assertEquals(3, servicio.listar().size());
    }
}