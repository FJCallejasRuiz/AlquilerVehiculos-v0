package vista;

import controlador.Controlador;

public class Vista {

	Controlador controlador;
	
	public void setControlador(Controlador controlador) {
		if(controlador == null) {
			throw new NullPointerException("ERROR: El controlador es nulo.");
		}
		this.controlador=controlador;
	}
	public void comenzar() {
		Opcion opcionTemp;
		Consola.mostrarCabecera("Bienvenido al gestor de alquiler de vehículos.");
		do {
			Consola.mostrarMenu();
			opcionTemp = Consola.elegirOpcion();
			ejecutar(opcionTemp);
		}while(opcionTemp != Opcion.SALIR);
		terminar();		
	}
	public void terminar() {
		System.out.println("El gestor de alquiler ha finalizado.");
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
	}
	
	private void insertarTurismo() {
		Consola.mostrarCabecera("Eligió insertar un turismo.");
	}
	
	private void insertarAlquiler() {
		Consola.mostrarCabecera("Eligió insertar un alquiler.");
	}
	
	private void buscarCliente() {
		Consola.mostrarCabecera("Eligió buscar un cliente.");
	}
	
	private void buscarTurismo() {
		Consola.mostrarCabecera("Eligió buscar un turismo.");
	}
	
	private void buscarAlquiler() {
		Consola.mostrarCabecera("Eligió buscar un alquiler.");
	}
	
	private void modificarCliente() {
		Consola.mostrarCabecera("Eligió modificar un cliente.");
	}
	
	private void devolverAlquiler() {
		Consola.mostrarCabecera("Eligió modificar un alquiler.");
	}
	
	private void borrarCliente() {
		Consola.mostrarCabecera("Eligió borrar un cliente.");
	}
	
	private void borrarTurismo() {
		Consola.mostrarCabecera("Eligió borrar un turismo.");
	}
	
	private void borrarAlquiler() {
		Consola.mostrarCabecera("Eligió borrar un alquiler.");
	}
	
	private void listarClientes() {
		Consola.mostrarCabecera("Eligió listar los clientes.");
	}
	
	private void listarTurismos() {
		Consola.mostrarCabecera("Eligió listar los turismos.");
	}
	
	private void listarAlquileres() {
		Consola.mostrarCabecera("Eligió listar los alquileres.");
	}
	
	private void listarAlquileresCliente() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un cliente.");
	}
	
	private void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un turismo.");
	}
}
