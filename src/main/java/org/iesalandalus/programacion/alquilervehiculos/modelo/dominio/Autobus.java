package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Autobus extends Vehiculo {

	private final int FACTOR_PLAZAS = 2;
	private int plazas;

	public Autobus(String marca, String modelo, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPlazas(plazas);
	}

	protected Autobus(Autobus autobus) {
		super(autobus);
		if (autobus == null) {
			throw new NullPointerException("ERROR: No es posible copiar una furgoneta nula.");
		} else {
			setMarca(autobus.getMarca());
			setModelo(autobus.getModelo());
			setMatricula(autobus.getMatricula());
			setPlazas(autobus.getPlazas());
		}

	}

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if (plazas < 1 || plazas > 50) {
			throw new IllegalArgumentException("ERROR: La cantidad de asientos no es correcta.");
		}
			this.plazas = plazas;
		}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(plazas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Furgoneta))
			return false;
		Furgoneta other = (Furgoneta) obj;
		return getMatricula() == other.getMatricula();
	}

	@Override
	protected int getFactorPrecio() {
		int resultadoFinal = this.getPlazas() * FACTOR_PLAZAS;
		return resultadoFinal;
	}

	@Override
	public String toString() {
		return super.toString() + " Autobus [ asientos= "+ plazas+"]";
	}

}