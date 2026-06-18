package com.krakedev.productos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.productos.entidades.Producto;

@Service
public class ProductoService {

	private List<Producto> productos;

	public ProductoService() {
		this.productos = new ArrayList<>();
	}

	public Producto crear(Producto producto) {
		for (Producto p : productos) {
			if (p.getCodigo() == producto.getCodigo()) {
				return null;
			}
		}

		productos.add(producto);
		return producto;
	}

	public List<Producto> listar() {
		return productos;
	}

	public Producto buscarPorCodigo(int codigo) {
		for (Producto producto : productos) {
			if (producto.getCodigo() == codigo) {
				return producto;
			}
		}
		return null;
	}

	public Producto actualizar(int codigo, Producto productoActualizado) {
		for (Producto producto : productos) {
			if (producto.getCodigo() == codigo) {
				producto.setNombre(productoActualizado.getNombre());
				producto.setPrecio(productoActualizado.getPrecio());
				producto.setStock(productoActualizado.getStock());
				return producto;
			}
		}
		return null;
	}

	public boolean eliminar(int codigo) {
		for (Producto producto : productos) {
			if (producto.getCodigo() == codigo) {
				productos.remove(producto);
				return true;
			}
		}
		return false;
	}
}