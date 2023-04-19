package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static String PATRON_FECHA = "dd/MM/yyyy";
	private static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		int longitud = mensaje.length();
		for (int i = 0; i <= longitud; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	public static void mostrarMenuAcciones() {
		System.out.println("Organizador de alquileres entre clientes y respectivos vehículos.");
		System.out.println("------------------------");
		System.out.println("Las opciones son: ");
		System.out.println("------------------------");
		int contador = 0;
		for (Accion accion : Accion.values()) {

			System.out.println(accion.ordinal() + 1 + " " + accion.toString());
			contador++;
			if (contador == 1 || contador == 4 || contador == 7 || contador == 9 || contador == 12 || contador == 15) {
				System.out.println("------------------------");
			}
		}
		System.out.println("------------------------");

	}

	private static String leerCadena(String mensaje) {
		System.out.println(mensaje);
		String cadenaALeer = Entrada.cadena();
		return cadenaALeer;
	}

	private static Integer leerEntero(String mensaje) {
		System.out.println(mensaje);
		int intALeer = Entrada.entero();
		return intALeer;
	}

	private static LocalDate leerFecha(String mensaje) throws DateTimeParseException {
		System.out.println(mensaje);
		String fechaTempo;
		do {
			fechaTempo = Entrada.cadena();
		} while (fechaTempo.length() != 10);
		LocalDate fechaConvertida = LocalDate.parse(fechaTempo, FORMATO_FECHA);
		return fechaConvertida;
	}

	public static Accion elegirAccion() {
		String mensaje = "Introduzca el número de la opción";

		int opcionInt = leerEntero(mensaje);
		while (opcionInt < 1 || opcionInt > 19) {
			System.out.println("Ese número no es posible, por favor inténtelo de nuevo.");
			opcionInt = leerEntero(mensaje);
		}
		opcionInt = opcionInt - 1;
		Accion opcionTempo = Accion.get(opcionInt);
		return opcionTempo;
	}

	public static Cliente leerCliente() {
		String dni = "Introduzca el dni del cliente.";
		String dniFormado = leerCadena(dni);
		Cliente clienteFormado = new Cliente(leerNombre(), dniFormado, leerTelefono());
		return clienteFormado;
	}

	public static Cliente leerClienteDni() {
		String dni = "Introduzca el dni del cliente.";
		String dniFormado = leerCadena(dni);
		return Cliente.getClienteConDni(dniFormado);
	}

	public static String leerNombre() {
		String nombre = "Introduzca el nombre del cliente.";
		String nombreFormado = leerCadena(nombre);
		return nombreFormado;
	}

	public static String leerTelefono() {
		String telefono = "Introduzca el telefono del cliente.";
		String telefonoFormado = leerCadena(telefono);
		return telefonoFormado;
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}

	private static void mostrarMenuTiposVehiculos() {
		System.out.println("Los tipos de vehículos son: ");
		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			System.out.println(tipoVehiculo.ordinal() + 1 + " " + tipoVehiculo.toString());
		}
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		String mensaje = "Introduzca el número de la opción";

		int opcionInt = leerEntero(mensaje);
		while (opcionInt < 1 || opcionInt > 3) {
			System.out.println("Ese número no es posible, por favor inténtelo de nuevo.");
			opcionInt = leerEntero(mensaje);
		}
		opcionInt = opcionInt - 1;
		TipoVehiculo opcionTempo = TipoVehiculo.get(opcionInt);
		return opcionTempo;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		if (tipoVehiculo == null) {
			throw new NullPointerException("ERROR: El tipo de vehículo no puede ser nulo.");
		}
		String marca = "Introduzca la marca del vehículo.";
		String modelo = "Introduzca el modelo del vehículo.";
		String plaza = "Introduzca las plazas del vehículo.";
		String matricula = "Introduzca la matrícula del vehículo.";
		String pma = "Introduzca el PMA del vehículo.";
		String cilindrada = "Introduzca la cilindrada del vehículo.";

		String marcaFormada = leerCadena(marca);
		String modeloFormado = leerCadena(modelo);
		String matriculaFormada = leerCadena(matricula);

		if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			int plazasFormada = leerEntero(plaza);
			Vehiculo autobus = new Autobus(marcaFormada, modeloFormado, plazasFormada, matriculaFormada);
			return autobus;
		} else if (tipoVehiculo == TipoVehiculo.FURGONETA) {
			int pmaFormado = leerEntero(pma);
			int plazasFormada = leerEntero(plaza);
			Vehiculo furgoneta = new Furgoneta(marcaFormada, modeloFormado, pmaFormado, plazasFormada,
					matriculaFormada);
			return furgoneta;
		} else if (tipoVehiculo == TipoVehiculo.TURISMO) {
			int cilindradaFormada = leerEntero(cilindrada);
			Vehiculo turismo = new Turismo(marcaFormada, modeloFormado, cilindradaFormada, matriculaFormada);
			return turismo;
		} else {
			return null;
		}

	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = "Introduzca la matrícula del vehículo.";
		String matriculaFormada = leerCadena(matricula);
		return Vehiculo.getVehiculoConMatricula(matriculaFormada);
	}

	public static Alquiler leerAlquiler() {
		Cliente clienteTemp = leerClienteDni();
		Vehiculo vehiculoTemp = leerVehiculoMatricula();
		String fecha = "introduzca la fecha de alquiler.";
		LocalDate fechaTemp = leerFecha(fecha);

		Alquiler alquiler = new Alquiler(clienteTemp, vehiculoTemp, fechaTemp);
		return alquiler;
	}

	public static LocalDate leerFechaDevolucion() {
		String fecha = "Introduzca la fecha de devolución.";
		LocalDate fechaFormada = leerFecha(fecha);
		return fechaFormada;
	}

	public static LocalDate leerMes() {
		System.out.println("Introduzca el mes del 1 al 12.");
		int fechaALeer;
		do {
			fechaALeer = Entrada.entero();
		} while (fechaALeer < 1 || fechaALeer > 12);
		switch (fechaALeer) {
		case 1:
			return LocalDate.of(1, 1, 23);
		case 2:
			return LocalDate.of(1, 2, 23);
		case 3:
			return LocalDate.of(1, 3, 23);
		case 4:
			return LocalDate.of(1, 4, 23);
		case 5:
			return LocalDate.of(1, 5, 23);
		case 6:
			return LocalDate.of(1, 6, 23);
		case 7:
			return LocalDate.of(1, 7, 23);
		case 8:
			return LocalDate.of(1, 8, 23);
		case 9:
			return LocalDate.of(1, 9, 23);
		case 10:
			return LocalDate.of(1, 10, 23);
		case 11:
			return LocalDate.of(1, 11, 23);
		case 12:
			return LocalDate.of(1, 12, 23);
		default:
			return null;
		}
		// Se que no tiene sentido este método pero como desconozco que pide exactamente
		// y dijiste que es para la proxima tarea.
		// Así que lo dejo a medias para corregirlo en la siguiente tarea ya que no hace
		// falta para esta.
	}
}