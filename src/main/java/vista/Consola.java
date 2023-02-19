package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.utilidades.Entrada;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;

public class Consola {
	private static String PATRON_FECHA="dd/MM/yyyy";
	private static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		int longitud = mensaje.length();
		for (int i = 0; i <= longitud; i++) {
			System.out.print("-");
		}
	}

	public static void mostrarMenu() {
		System.out.println("Organizador de alquileres entre clientes y respectivos turismos.");
		System.out.println("------------------------");
		System.out.println("Las opciones son: ");
		System.out.println("------------------------");
		System.out.println("1. Salir.");
		System.out.println("------------------------");
		System.out.println("2. Insertar un cliente.");
		System.out.println("3. Insertar un turismo.");
		System.out.println("4. Insertar un alquiler.");
		System.out.println("------------------------");
		System.out.println("5. Buscar un cliente.");
		System.out.println("6. Buscar un turismo.");
		System.out.println("7  Buscar un alquiler.");
		System.out.println("------------------------");
		System.out.println("8. Modificar un cliente.");
		System.out.println("9. Devolver un alquiler.");
		System.out.println("------------------------");
		System.out.println("10. Borrar un cliente.");
		System.out.println("11. Borrar un turismo.");
		System.out.println("12. Borrar un alquiler");
		System.out.println("------------------------");
		System.out.println("13  Listar los clientes.");
		System.out.println("14. Listar los turismos.");
		System.out.println("15. Listar los alquileres.");
		System.out.println("------------------------");
		System.out.println("16. Listar los alquileres de un cliente.");
		System.out.println("17  Listar los alquileres de un turismo.");
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

	private static LocalDate leerFecha(String mensaje) {
		System.out.println(mensaje);
		String fechaTempo;
		do {
			fechaTempo = Entrada.cadena();
		} while (fechaTempo.length() != 10);
		LocalDate fechaConvertida = LocalDate.parse(fechaTempo, FORMATO_FECHA);
		return fechaConvertida;
	}

	public static Opcion elegirOpcion() {
		String mensaje = "Introduzca el número de la opción";

		int opcionInt = leerEntero(mensaje);
		while (opcionInt < 1 || opcionInt > 17) {
			System.out.println("Ese número no es posible, por favor inténtelo de nuevo.");
			opcionInt = leerEntero(mensaje);
		}
		opcionInt = opcionInt - 1;
		Opcion opcionTempo = Opcion.get(opcionInt);
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
		Cliente clienteFormado = new Cliente(leerNombre(), dniFormado, "693310060");
		return clienteFormado;
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

	public static Turismo leerTurismo() {
		String marca = "Introduzca el dni del cliente.";
		String modelo = "Introduzca el dni del cliente.";
		String cilindrada = "Introduzca la cilindrada del cliente";
		String matricula = "Introduzca el dni del cliente.";

		String marcaFormada = leerCadena(marca);
		String modeloFormado = leerCadena(modelo);
		int cilindradaFormada = leerEntero(cilindrada);
		String matriculaFormada = leerCadena(matricula);

		Turismo turismoFormado = new Turismo(marcaFormada, modeloFormado, cilindradaFormada, matriculaFormada);
		return turismoFormado;
	}

	public static Turismo leerTurismoMatricula() {
		String matricula = "Introduzca el dni del cliente.";
		String matriculaFormada = leerCadena(matricula);
		Turismo turismoFormado = new Turismo("BMW", "A", 500, matriculaFormada);
		return turismoFormado;
	}


	public static Alquiler leerAlquiler() {
		Cliente clienteTemp = leerClienteDni();
		Turismo turismoTemp = leerTurismoMatricula();
		String fecha = "introduzca la fecha de alquiler.";
		LocalDate fechaTemp = leerFecha(fecha);

		Alquiler alquiler = new Alquiler(clienteTemp, turismoTemp, fechaTemp);
		return alquiler;
	}

	public static LocalDate leerFechaDevolucion() {
		String fecha = "Introduzca la fecha de devolución.";
		LocalDate fechaFormada = leerFecha(fecha);
		return fechaFormada;
	}
}