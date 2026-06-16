package com.krakedev.taller.taller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.taller.taller.entidades.Vehiculo;
import com.krakedev.taller.taller.repositories.VehiculoRepository;

@Service
public class VehiculoService {

	private final VehiculoRepository repository;

	public VehiculoService(VehiculoRepository repository) {
		this.repository = repository;
	}

	public Vehiculo guardar(Vehiculo vehiculo) {
		Optional<Vehiculo>vehiculoExistente=repository.findById(vehiculo.getPlaca());
		
		if(vehiculoExistente.isPresent()){
			throw new RuntimeException("El vehiculo ya existe");
		}
		
		return repository.save(vehiculo);
	}
	
	public List<Vehiculo> listar() {
		return repository.findAll();
	}
	
	public Vehiculo buscar(String id) {
		Optional<Vehiculo>resultado=repository.findById(id);
		
		return resultado.orElse(null);
	}
	
	public Vehiculo actualizar(String id, Vehiculo datos) {
		Vehiculo vehiculo=buscar(id);
		
		if(vehiculo==null) {
			return null;
		}
		
		vehiculo.setAnio(datos.getAnio());
		vehiculo.setMarca(datos.getMarca());
		vehiculo.setModelo(datos.getModelo());
		vehiculo.setRepaciones(datos.getRepaciones());
		
		return repository.save(vehiculo);
	}
	
	public boolean eliminar(String id) {
		Vehiculo vehiculo=buscar(id);
		
		if(vehiculo==null) {
			return false;
		}
		
		repository.deleteById(id);
		
		return true;
	}

}
