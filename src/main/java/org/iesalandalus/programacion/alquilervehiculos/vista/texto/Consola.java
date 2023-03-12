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
		System.out.println("1. Salir.");
		System.out.println("------------------------");
		System.out.println("2. Insertar un cliente.");
		System.out.println("3. Insertar un vehículo.");
		System.out.println("4. Insertar un alquiler.");
		System.out.println("------------------------");
		System.out.println("5. Buscar un cliente.");
		System.out.println("6. Buscar un vehículo.");
		System.out.println("7  Buscar un alquiler.");
		System.out.println("------------------------");
		System.out.println("8. Modificar un cliente.");
		System.out.println("9. Devolver un alquiler.");
		System.out.println("------------------------");
		System.out.println("10. Borrar un cliente.");
		System.out.println("11. Borrar un vehículo.");
		System.out.println("12. Borrar un alquiler.");
		System.out.println("------------------------");
		System.out.println("13  Listar los clientes.");
		System.out.println("14. Listar los vehículos.");
		System.out.println("15. Listar los alquileres.");
		System.out.println("------------------------");
		System.out.println("16. Listar los alquileres de un cliente.");
		System.out.println("17  Listar los alquileres de un vehículo.");
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
		while (opcionInt < 1 || opcionInt > 17) {
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
		System.out.println("1.- " + TipoVehiculo.TURISMO);
		System.out.println("2.- " + TipoVehiculo.AUTOBUS);
		System.out.println("3.- " + TipoVehiculo.FURGONETA);
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
		LocalDate ahora = LocalDate.now();
		System.out.println("Introduzca el mes del 1 al 12.");
		int fechaALeer;
		do {
			fechaALeer = Entrada.entero();
		} while (fechaALeer<1 || fechaALeer>12);
		return ahora;
		//Se que no tiene sentido este método pero como desconozco que pide exactamente y dijiste que es para la proxima tarea.
		//Así que lo dejo a medias para corregirlo en la siguiente tarea ya que no hace falta para esta.
	}
}