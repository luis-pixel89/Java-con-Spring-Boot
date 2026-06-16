package com.krakedev.taller.taller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.taller.taller.entidades.Mecanico;
import com.krakedev.taller.taller.repositories.MecanicoRepository;

@Service
public class MecanicoService {

	private final MecanicoRepository repository;

	public MecanicoService(MecanicoRepository repository) {
		this.repository = repository;
	}

	public Mecanico guardar(Mecanico mecanico) {
		return repository.save(mecanico);
	}

	public List<Mecanico> listar() {
		return repository.findAll();
	}
	
	public Mecanico buscar(int id) {
		Optional<Mecanico>resultado=repository.findById(id);
		
		return resultado.orElse(null);
	}
	
	public Mecanico actualizar(int id, Mecanico datos) {
		Mecanico mecanico=buscar(id);
		
		if(mecanico==null) {
			return null;
		}
		
		mecanico.setEspecialidad(datos.getEspecialidad());
		mecanico.setNombre(datos.getNombre());
		mecanico.setReparaciones(datos.getReparaciones());
		
		return repository.save(mecanico);
	}
	
	public boolean eliminar(int id) {
		Mecanico mecanico=buscar(id);
		
		if(mecanico==null) {
			return false;
		}
		
		repository.deleteById(id);
		
		return true;
	}
	

}
