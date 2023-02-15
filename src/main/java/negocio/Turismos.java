package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Turismo;

public class Turismos {

	List<Turismo> coleccionTurismos;

	public Turismos() {
		coleccionTurismos = new ArrayList<Turismo>();
	}
	
	public List<Turismo> get() {

		List<Turismo> coleccionTemporal = new ArrayList<Turismo>();
		for (Turismo turismo : coleccionTurismos) {
			coleccionTemporal.add(turismo);
		}
		return coleccionTemporal;
	}

	public int getCantidad() {
		int cantidad = coleccionTurismos.size();

		return cantidad;
	}
	
	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
		} else if (!coleccionTurismos.contains(turismo)) {
			coleccionTurismos.add(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
		}
	}
	
	public Turismo buscar(Turismo turismo) {

		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
		} else if (coleccionTurismos.contains(turismo)) {
			return turismo;
		} else {
			return null;
		}
	}
	
	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
		} else if (coleccionTurismos.contains(turismo)) {
			coleccionTurismos.remove(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
		}
	}
	
}
