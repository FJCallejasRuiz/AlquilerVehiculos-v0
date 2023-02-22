package modelo;

import negocio.Clientes;
import negocio.Turismos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;
import negocio.Alquileres;

public class Modelo {
	private Clientes clientes;
	private Alquileres alquileres;
	private Turismos turismos;

	public void comenzar() {
		clientes = new Clientes();
		alquileres = new Alquileres();
		turismos = new Turismos();
	}

	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		Cliente clienteTemporal = new Cliente(cliente);
		this.clientes.insertar(clienteTemporal);
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		Turismo turismoTemporal = new Turismo(turismo);
		this.turismos.insertar(turismoTemporal);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente clienteTemporal = clientes.buscar(alquiler.getCliente());
		Turismo turismoTemporal = turismos.buscar(alquiler.getTurismo());
		if (clienteTemporal == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		} else if (turismoTemporal == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		} else {
			Alquiler alquilerTemporal = new Alquiler(clienteTemporal, turismoTemporal, alquiler.getFechaAlquiler());
			this.alquileres.insertar(alquilerTemporal);
		}
	}

	public Cliente buscar(Cliente cliente) {
		Cliente clienteBuscado = new Cliente(clientes.buscar(cliente));
		return clienteBuscado;
	}

	public Turismo buscar(Turismo turismo) {
		Turismo turismoBuscado = new Turismo(turismos.buscar(turismo));
		return turismoBuscado;
	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerBuscado = new Alquiler(alquileres.buscar(alquiler));
		return alquilerBuscado;
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		this.clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechadevolucion) throws OperationNotSupportedException {
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
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : this.alquileres.get(cliente)) {
			this.alquileres.borrar(alquiler);
			this.clientes.borrar(cliente);
		}
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(turismo)) {
			this.alquileres.borrar(alquiler);
			this.turismos.borrar(turismo);
		}
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

	public List<Turismo> getTurismos() {

		List<Turismo> coleccionTemporal = new ArrayList<Turismo>();
		Turismo turismoTemporal;
		for (Turismo turismo : turismos.get()) {
			turismoTemporal = new Turismo(turismo);
			coleccionTemporal.add(turismoTemporal);
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

	public List<Alquiler> getAlquileres(Turismo turismo) {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();
		Alquiler alquilerTemporal;
		for (Alquiler alquiler : alquileres.get(turismo)) {
			alquilerTemporal = new Alquiler(alquiler);
			coleccionTemporal.add(alquilerTemporal);
		}
		return coleccionTemporal;
	}
}
