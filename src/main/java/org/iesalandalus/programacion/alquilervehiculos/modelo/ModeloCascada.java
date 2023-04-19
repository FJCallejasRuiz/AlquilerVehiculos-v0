package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		Cliente clienteTemporal = new Cliente(cliente);
		this.clientes.insertar(clienteTemporal);
	}

	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		Vehiculo vehiculoTemporal = Vehiculo.copiar(vehiculo);
		this.vehiculos.insertar(vehiculoTemporal);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente clienteTemporal = clientes.buscar(alquiler.getCliente());
		Vehiculo vehiculoTemporal = vehiculos.buscar(alquiler.getVehiculo());
		if (clienteTemporal == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		} else if (vehiculoTemporal == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		} else {
			Alquiler alquilerTemporal = new Alquiler(clienteTemporal, vehiculoTemporal, alquiler.getFechaAlquiler());
			this.alquileres.insertar(alquilerTemporal);
		}
	}

	public Cliente buscar(Cliente cliente) {
		if (clientes.buscar(cliente) == null) {
			return null;
		} else {
			Cliente clienteBuscado = new Cliente(clientes.buscar(cliente));
			return clienteBuscado;
		}

	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculos.buscar(vehiculo) == null) {
			return null;
		} else {
			Vehiculo vehiculoBuscado = Vehiculo.copiar(vehiculos.buscar(vehiculo)); // mmmmmh
			return vehiculoBuscado;
		}

	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquileres.buscar(alquiler) == null) {
			return null;
		} else {
			Alquiler alquilerBuscado = new Alquiler(alquileres.buscar(alquiler));
			return alquilerBuscado;
		}

	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		this.clientes.modificar(cliente, nombre, telefono);
	}

	/*public void devolver(Alquiler alquiler, LocalDate fechadevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (fechadevolucion == null) {
			throw new NullPointerException("ERROR: No se puede realizar una devolución nula.");
		}
		if (this.alquileres.buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		} else
			alquiler.devolver(fechadevolucion);
	}*/
	
	public void devolver(Cliente cliente, LocalDate fechadevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler con cliente nulo.");
		}
		if (fechadevolucion == null) {
			throw new NullPointerException("ERROR: No se puede realizar una devolución nula.");
		}
			alquileres.devolver(cliente, fechadevolucion);
	}
	
	public void devolver(Vehiculo vehiculo, LocalDate fechadevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (fechadevolucion == null) {
			throw new NullPointerException("ERROR: No se puede realizar una devolución nula.");
		}
			alquileres.devolver(vehiculo,fechadevolucion);
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : this.alquileres.get(cliente)) {
			alquileres.borrar(alquiler);
		}
		this.clientes.borrar(cliente);
	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(vehiculo)) {
			alquileres.borrar(alquiler);

		}
		this.vehiculos.borrar(vehiculo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		this.alquileres.borrar(alquiler);
	}

	public List<Cliente> getClientes() {

		List<Cliente> coleccionTemporal = new ArrayList<Cliente>();
		Cliente clienteTemporal;
		for (Cliente cliente : clientes.get()) {
			clienteTemporal = new Cliente(cliente);
			coleccionTemporal.add(clienteTemporal);
		}
		return coleccionTemporal;
	}

	public List<Vehiculo> getVehiculos() {

		List<Vehiculo> coleccionTemporal = new ArrayList<Vehiculo>();
		Vehiculo vehiculoTemporal;
		for (Vehiculo vehiculo : vehiculos.get()) {
			vehiculoTemporal = Vehiculo.copiar(vehiculo); // mmmmh
			coleccionTemporal.add(vehiculoTemporal);
		}
		return coleccionTemporal;
	}

	public List<Alquiler> getAlquileres() {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();
		Alquiler alquilerTemporal;
		for (Alquiler alquiler : alquileres.get()) {
			alquilerTemporal = new Alquiler(alquiler);
			coleccionTemporal.add(alquilerTemporal);
		}
		return coleccionTemporal;
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();
		Alquiler alquilerTemporal;
		for (Alquiler alquiler : alquileres.get(cliente)) {
			alquilerTemporal = new Alquiler(alquiler);
			coleccionTemporal.add(alquilerTemporal);
		}
		return coleccionTemporal;
	}

	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();
		Alquiler alquilerTemporal;
		for (Alquiler alquiler : alquileres.get(vehiculo)) {
			alquilerTemporal = new Alquiler(alquiler);
			coleccionTemporal.add(alquilerTemporal);
		}
		return coleccionTemporal;
	}
}
