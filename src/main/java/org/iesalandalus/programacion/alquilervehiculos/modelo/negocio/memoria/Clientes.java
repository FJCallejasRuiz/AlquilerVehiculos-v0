package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;

public class Clientes implements IClientes {
	List<Cliente> coleccionClientes;


	public Clientes() {
		coleccionClientes = new ArrayList<Cliente>();
	}

	
	@Override
	public List<Cliente> get() {

		List<Cliente> coleccionTemporal = new ArrayList<Cliente>();
		for (Cliente cliente : coleccionClientes) {
			coleccionTemporal.add(cliente);
		}
		return coleccionTemporal;
	}

	@Override
	public int getCantidad() {
		int cantidad = coleccionClientes.size();

		return cantidad;
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		} else if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	@Override
	public Cliente buscar(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			int index = coleccionClientes.indexOf(cliente);
			return coleccionClientes.get(index);
		} else {
			return null;
		}
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");

		} else if (coleccionClientes.contains(cliente)) {

			int index = coleccionClientes.indexOf(cliente);
			if (nombre != null) {
				coleccionClientes.get(index).setNombre(nombre);
			}
			if (telefono != null) {
				coleccionClientes.get(index).setTelefono(telefono);
			}

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

	}
}