package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	public static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler, fechaDevolucion;
	private Cliente cliente;
	private Vehiculo vehiculo;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) {
		this.setCliente(cliente);
		this.setVehiculo(vehiculo);
		this.setFechaAlquiler(fechaAlquiler);
		this.fechaDevolucion = null;
	}

	public Alquiler(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		} else {
			cliente = new Cliente(alquiler.cliente);
			if (alquiler.vehiculo instanceof Turismo) {
				vehiculo = new Turismo((Turismo) vehiculo);
			} else if (alquiler.vehiculo instanceof Autobus) {
				vehiculo = new Autobus((Autobus) vehiculo);
			} else if (alquiler.vehiculo instanceof Furgoneta) {
				vehiculo = new Furgoneta((Furgoneta) vehiculo);
			}
			fechaAlquiler = alquiler.getFechaAlquiler();
			if (alquiler.getFechaDevolucion() == null) {
				fechaDevolucion = null;
			} else
				fechaDevolucion = alquiler.getFechaDevolucion();
		}
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if(fechaDevolucion == null) {
			throw new NullPointerException("ERROR: Es fecha nula.");
		}
		if (this.getFechaDevolucion() != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		this.setFechaDevolucion(fechaDevolucion);
	}

	public int getPrecio() {
		if (this.getFechaDevolucion() == null) {
			return 0;
		} else {
			LocalDate fechaAlquilerD = this.getFechaAlquiler();
			LocalDate fechaDevolucionD = this.getFechaDevolucion();
			long diasDiferencia = Duration.between(fechaAlquilerD.atStartOfDay(), fechaDevolucionD.atStartOfDay())
					.toDays();
			int numDias = (int) diasDiferencia;

			int precio = (PRECIO_DIA + vehiculo.getFactorPrecio()) * numDias;

			return precio;
		}

	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		} else if (fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		} else {
			this.fechaAlquiler = fechaAlquiler;
		}
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		} else if (fechaDevolucion.isBefore(this.fechaAlquiler)) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		} else if (fechaDevolucion.equals(this.fechaAlquiler)) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		} else if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		} else {
			this.fechaDevolucion = fechaDevolucion;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;

	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	private void setVehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El vehículo no puede ser nulo.");
		}
		this.vehiculo = vehiculo;

	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alquiler))
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public String toString() {
		if (fechaDevolucion != null) {
			return cliente + " <---> " + vehiculo + ", " + fechaAlquiler.format(FORMATO_FECHA) + " - "
					+ fechaDevolucion.format(FORMATO_FECHA) + " (" + this.getPrecio() + "€)";

		} else {
			return cliente + " <---> " + vehiculo + ", " + fechaAlquiler.format(FORMATO_FECHA) + " - Aún no devuelto"
					+ " (" + this.getPrecio() + "€)";
		}

	}

}
