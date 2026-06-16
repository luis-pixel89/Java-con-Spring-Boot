package com.krakedev.taller.taller.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.taller.taller.entidades.Mecanico;
import com.krakedev.taller.taller.entidades.Reparacion;
import com.krakedev.taller.taller.entidades.Vehiculo;
import com.krakedev.taller.taller.repositories.MecanicoRepository;
import com.krakedev.taller.taller.repositories.ReparacionRepository;
import com.krakedev.taller.taller.repositories.VehiculoRepository;

@Service
public class ReparacionService {

	private final ReparacionRepository repository;
	private final VehiculoRepository vehiculoRepository;
	private final MecanicoRepository mecanicoRepository;

	public ReparacionService(ReparacionRepository repository, VehiculoRepository vehiculoRepository,
			MecanicoRepository mecanicoRepository) {
		this.repository = repository;
		this.vehiculoRepository = vehiculoRepository;
		this.mecanicoRepository = mecanicoRepository;
	}

	public Reparacion guardar(Reparacion reparacion) {
		Vehiculo vehiculo = vehiculoRepository.findById(reparacion.getVehiculo().getPlaca())
				.orElseThrow(() -> new RuntimeException("Vehiculo no existe"));

		List<Mecanico> mecanicosDB = new ArrayList<>();

		for (Mecanico mecanico : reparacion.getMecanicos()) {
			Mecanico mecanicoReal = mecanicoRepository.findById(mecanico.getId())
					.orElseThrow(() -> new RuntimeException("Mecanico no existe"));

			mecanicosDB.add(mecanicoReal);
		}

		reparacion.setVehiculo(vehiculo);
		reparacion.setMecanicos(mecanicosDB);

		return repository.save(reparacion);
	}

	public List<Reparacion> listar() {
		return repository.findAll();
	}

	public Reparacion buscar(int id) {
		Optional<Reparacion> resultado = repository.findById(id);

		return resultado.orElse(null);
	}

	public Reparacion actualizar(int id, Reparacion datos) {
		Reparacion reparacion = buscar(id);

		if (reparacion == null) {
			return null;
		}

		reparacion.setCosto(datos.getCosto());
		reparacion.setDescripcion(datos.getDescripcion());
		reparacion.setFechaIngreso(datos.getFechaIngreso());
		reparacion.setFechaEntrega(datos.getFechaEntrega());

		return repository.save(reparacion);
	}
	
	public boolean eliminar(int id ) {
		Reparacion reparacion=buscar(id);
		
		if(reparacion==null) {
			return false;
		}
		
		repository.deleteById(id);
		
		return true;
	}
}
