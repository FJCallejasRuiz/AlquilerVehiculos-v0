package vista;

import javax.naming.OperationNotSupportedException;

import controlador.Controlador;
import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;

public class Vista {

	Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador es nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		Opcion opcionTemp;
		Consola.mostrarCabecera("Bienvenido al gestor de alquiler de vehículos.");
		do {
			Consola.mostrarMenu();
			opcionTemp = Consola.elegirOpcion();
			ejecutar(opcionTemp);
		} while (opcionTemp != Opcion.SALIR);
		terminar();
	}

	public void terminar() {
		System.out.println("El gestor de alquiler ha finalizado.");
		System.exit(0);
	}

	private void ejecutar(Opcion opcion) {
		switch (opcion) {
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case INSERTAR_TURISMO:
			insertarTurismo();
			break;
		case INSERTAR_ALQUILER:
			insertarAlquiler();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BUSCAR_TURISMO:
			buscarTurismo();
			break;
		case BUSCAR_ALQUILER:
			buscarAlquiler();
			break;
		case MODIFICAR_CLIENTE:
			modificarCliente();
			break;
		case DEVOLVER_ALQUILER:
			devolverAlquiler();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case BORRAR_TURISMO:
			borrarTurismo();
			break;
		case BORRAR_ALQUILER:
			borrarAlquiler();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_TURISMOS:
			listarTurismos();
			break;
		case LISTAR_ALQUILERES:
			listarAlquileres();
			break;
		case LISTAR_ALQUILERES_CLIENTE:
			listarAlquileresCliente();
			break;
		case LISTAR_ALQUILERES_TURISMO:
			listarAlquileresTurismo();
			break;
		default:
			break;
		}
	}

	private void insertarCliente() {
		Consola.mostrarCabecera("Eligió insertar un cliente.");
		try {
			controlador.insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarTurismo() {
		Consola.mostrarCabecera("Eligió insertar un turismo.");
		try {
			controlador.insertar(Consola.leerTurismo());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarAlquiler() {
		Consola.mostrarCabecera("Eligió insertar un alquiler.");
		try {
			controlador.insertar(Consola.leerAlquiler());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarCliente() {
		Consola.mostrarCabecera("Eligió buscar un cliente.");
		try {
			if (controlador.buscar(Consola.leerCliente()) == null) {
				System.out.println("El cliente no se encuentra en la base de datos.");
			} else {
				System.out.println("El cliente se encuentra en la base de datos.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	private void buscarTurismo() {
		Consola.mostrarCabecera("Eligió buscar un turismo.");
		try {
			if (controlador.buscar(Consola.leerTurismo()) == null) {
				System.out.println("El turismo no se encuentra en la base de datos.");
			} else {
				System.out.println("El turismo se encuentra en la base de datos.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarAlquiler() {
		Consola.mostrarCabecera("Eligió buscar un alquiler.");
		try {
			if (controlador.buscar(Consola.leerAlquiler()) == null) {
				System.out.println("El alquiler no se encuentra en la base de datos.");
			} else {
				System.out.println("El alquiler se encuentra en la base de datos.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void modificarCliente() {
		Consola.mostrarCabecera("Eligió modificar un cliente.");
		try {
			controlador.modificar(controlador.buscar(Consola.leerCliente()), Consola.leerNombre(),
					Consola.leerTelefono());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void devolverAlquiler() {
		Consola.mostrarCabecera("Eligió modificar un alquiler.");
		try {
			controlador.devolver(controlador.buscar(Consola.leerAlquiler()), Consola.leerFechaDevolucion());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarCliente() {
		Consola.mostrarCabecera("Eligió borrar un cliente.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerCliente()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarTurismo() {
		Consola.mostrarCabecera("Eligió borrar un turismo.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerTurismo()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarAlquiler() {
		Consola.mostrarCabecera("Eligió borrar un alquiler.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerAlquiler()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarClientes() {
		Consola.mostrarCabecera("Eligió listar los clientes.");
		for (Cliente cliente : controlador.getClientes()) {
			System.out.println(cliente);
		}

	}

	private void listarTurismos() {
		Consola.mostrarCabecera("Eligió listar los turismos.");
		for (Turismo turismo : controlador.getTurismos()) {
			System.out.println(turismo);
		}
	}

	private void listarAlquileres() {
		Consola.mostrarCabecera("Eligió listar los alquileres.");
		for (Alquiler alquiler : controlador.getAlquileres()) {
			System.out.println(alquiler);
		}
	}

	private void listarAlquileresCliente() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un cliente.");
		for (Alquiler alquiler : controlador.getAlquileres(Consola.leerCliente())) {
			System.out.println(alquiler);
		}
	}

	private void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un turismo.");
		for (Alquiler alquiler : controlador.getAlquileres(Consola.leerTurismo())) {
			System.out.println(alquiler);
		}
	}
}
