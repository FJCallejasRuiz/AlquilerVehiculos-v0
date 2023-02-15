package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Cliente;

public class Clientes {
	List<Cliente> coleccionClientes;

	public Clientes() {
		coleccionClientes = new ArrayList<Cliente>();
	}

	public List<Cliente> get() {

		List<Cliente> coleccionTemporal = new ArrayList<Cliente>();
		for (Cliente cliente : coleccionClientes) {
			coleccionTemporal.add(cliente);
		}
		return coleccionTemporal;
	}

	public int getCantidad() {
		int cantidad = coleccionClientes.size();

		return cantidad;
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		} else if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	public Cliente buscar(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			return cliente;
		} else {
			return null;
		}
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		} else if (nombre == null) {
			throw new NullPointerException("ERROR nombreqq NO");
		} else if (telefono == null) {
			throw new NullPointerException("ERROR telefonoE NO");
		} else if (coleccionClientes.contains(cliente)) {
			Cliente clienteNuevo = new Cliente(cliente);
			int index = coleccionClientes.indexOf(cliente);
			if (cliente.getNombre() != null || !cliente.getNombre().trim().isEmpty()) {
				clienteNuevo.setNombre(nombre);
			}
			if (cliente.getTelefono() != null || !cliente.getTelefono().trim().isEmpty()) {
				clienteNuevo.setTelefono(telefono);
			}
			coleccionClientes.set(index, clienteNuevo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

	}
}