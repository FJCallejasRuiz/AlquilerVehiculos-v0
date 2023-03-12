package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo extends Vehiculo {

	private final int FACTOR_CILINDRADA = 10;
	private int cilindrada;

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
	}

	protected Turismo(Turismo turismo) {
		super(turismo);
		if (turismo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		} else {
			setMarca(turismo.getMarca());
			setModelo(turismo.getModelo());
			setMatricula(turismo.getMatricula());
			setCilindrada(turismo.getCilindrada());
		}

	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada < 1 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		this.cilindrada = cilindrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cilindrada);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Turismo))
			return false;
		Turismo other = (Turismo) obj;
		return getMatricula() == other.getMatricula();
	}

	@Override
	protected int getFactorPrecio() {
		int resultadoFinal = this.getCilindrada() / FACTOR_CILINDRADA;
		return resultadoFinal;
	}

	@Override
	public String toString() {
		return super.toString() + " Turismo [cilindrada= " + cilindrada + " CV]";
	}

}
