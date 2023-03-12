package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Furgoneta extends Vehiculo {

	private final int FACTOR_PLAZAS = 1;
	private final int FACTOR_PMA = 100;
	private int plazas;
	private int pma;

	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPma(pma);
		setPlazas(plazas);
	}

	protected Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		if (furgoneta == null) {
			throw new NullPointerException("ERROR: No es posible copiar una furogneta nula.");
		} else {
			setMarca(furgoneta.getMarca());
			setModelo(furgoneta.getModelo());
			setMatricula(furgoneta.getMatricula());
			setPma(furgoneta.getPma());
			setPlazas(furgoneta.getPlazas());
		}

	}

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if (plazas < 1 || plazas > 5) {
			throw new IllegalArgumentException("ERROR: La cantidad de asientos no es correcta.");
		}
		this.plazas = plazas;
	}

	public int getPma() {
		return pma;
	}

	private void setPma(int pma) {
		if (pma < 1 || pma > 1000) {
			throw new IllegalArgumentException("ERROR: el peso no es correcto.");
		}
		this.pma = pma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(plazas, pma);
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
		int resultadoFinal = (this.getPlazas() * FACTOR_PLAZAS) + (this.getPma() / FACTOR_PMA);
		return resultadoFinal;
	}

	@Override
	public String toString() {
		return super.toString() + " Furgoneta [Asientos= " + plazas+ " y " +  pma + " kilos]";
	}

}
