package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {
	private final String ER_MARCA = "([A-Z][a-zA-Z]+)([\s][A-Z][a-z]+)*([-][A-Z][a-z]+)*([A-Z][a-z]+)*";
	private final String ER_MATRICULA = "([0-9]{4})([BCDFGHJKLMNPRSTVWXYZ]{3})";
	private String marca, modelo, matricula;


	protected Vehiculo(String marca, String modelo,  String matricula) {

		this.setMarca(marca);
		this.setModelo(modelo);
		this.setMatricula(matricula);
	}

	protected Vehiculo(Vehiculo vehiculo) { // constructor copia para evitar aliasing.
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		} else {
			marca = vehiculo.getMarca();
			modelo = vehiculo.getModelo();
			matricula = vehiculo.getMatricula();

		}

	}

	public static Vehiculo getVehiculoConMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if (matricula.trim().isEmpty()) {
			throw new IllegalArgumentException("La matrícula está vacía.");
		}
		Vehiculo test = new Turismo("BMW", "A", 500, matricula);
		return test;

	}
	
	protected abstract int getFactorPrecio();
	
	public static Vehiculo copiar(Vehiculo vehiculo) { //ESTATICO NO ES LO MAS PROBABLE
		Vehiculo vehiculoFinal = null;
		if(vehiculo == null) {
			throw new NullPointerException("ERROR: Vehículo nulo.");
		}else {
			if (vehiculo instanceof Turismo) {
				vehiculoFinal = new Turismo((Turismo)vehiculo);		
			} else if (vehiculo instanceof Autobus) {
				vehiculoFinal = new Autobus((Autobus)vehiculo);
			}else if (vehiculo instanceof Furgoneta) {
				vehiculoFinal= new Furgoneta((Furgoneta)vehiculo);	
			}
		}
	return vehiculoFinal;
		
	}

	public String getMarca() {
		
		return marca;
	}

	protected void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		} else if ((marca.matches(ER_MARCA))) {
			this.marca = marca;
		} else
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
	}

	public String getModelo() {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		return modelo;
	}

	protected void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		} else if (modelo.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		} else
			this.modelo = modelo;

	}

	public String getMatricula() {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		return matricula;
	}

	protected void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		} else if ((matricula.matches(ER_MATRICULA))) {
			this.matricula = matricula;
		} else
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

	@Override
	public String toString() {
		return marca + " " + modelo + "  " + matricula;
	}

}
