package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private final String ER_NOMBRE ="([A-Z][a-z]+)([\s][A-Z][a-z]+)*";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String nombre, dni, telefono;

	public Cliente(String nombre, String dni, String telefono) {

		this.setNombre(nombre);
		this.setDni(dni);
		this.setTelefono(telefono);
	}

	public Cliente(Cliente cliente) { // constructor copia para evitar aliasing.
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else {
			nombre = cliente.getNombre();
			dni = cliente.getDni();
			telefono = cliente.getTelefono();
		}

	}

	public static Cliente getClienteConDni(String dni) {
		Cliente test = new Cliente("Paquito", dni, "693310060");
		return test;

	}

	private boolean comprobarLetraDni(String dni) {

		Pattern patron;
		Matcher compara;

		patron = Pattern.compile(ER_DNI);
		compara = patron.matcher(dni);
		if (compara.matches()) {
			char[] LETRA_DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
					'V', 'H', 'L', 'C', 'K', 'E' };
			int dniNumero = Integer.parseInt(compara.group(1));
			char letraTempDni = LETRA_DNI[dniNumero % 23];

			String letraStringDni = String.valueOf(letraTempDni);

			if (letraStringDni.matches(compara.group(2))) {
				return true;
			}
		} else
			return false;
		return false;

	}

	public String getNombre() {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if ((nombre.matches(ER_NOMBRE))) {
			this.nombre = nombre;
		} else
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
	}

	public String getDni() {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		Pattern patronDni;
		Matcher comparaDni;
		patronDni = Pattern.compile(ER_DNI);
		comparaDni = patronDni.matcher(dni);

		if (comparaDni.matches()) {
			if (comprobarLetraDni(dni)) {
				this.dni = dni;
			} else
				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");

		} else {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
	}

	public String getTelefono() {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		} else if ((telefono.matches(ER_TELEFONO))) {
			this.telefono = telefono;
		} else
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return nombre + " - " + dni + " (" + telefono + ")";
	}

	

	
	
	
}
