package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {

	List<Vehiculo> coleccionVehiculos;


	public Vehiculos() {
		coleccionVehiculos = new ArrayList<Vehiculo>();
	}
	


	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> coleccionTemporal = new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : coleccionVehiculos) {
			coleccionTemporal.add(vehiculo);
		}
		return coleccionTemporal;
	}

	@Override
	public int getCantidad() {
		int cantidad = coleccionVehiculos.size();

		return cantidad;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		} else if (!coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.add(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");
		}
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
		} else if (coleccionVehiculos.contains(vehiculo)) {
			int index = coleccionVehiculos.indexOf(vehiculo);
			return coleccionVehiculos.get(index);
		} else {
			return null;
		}
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		} else if (coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.remove(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
		}
	}

}
