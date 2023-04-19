package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public void comenzar() {
		Accion opcionTemp = null;
		Consola.mostrarCabecera("Bienvenido al gestor de alquiler de vehículos.");

		do {
			Consola.mostrarMenuAcciones();
			try {
				opcionTemp = Consola.elegirAccion();
				ejecutar(opcionTemp);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		} while (opcionTemp != Accion.SALIR);
		terminar();
		

	}

	public void terminar() {
		System.out.println("El gestor de alquiler ha finalizado.");
		System.exit(0);
	}

	private void ejecutar(Accion opcion) {
		switch (opcion) {
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case INSERTAR_VEHICULO:
			insertarVehiculo();
			break;
		case INSERTAR_ALQUILER:
			insertarAlquiler();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BUSCAR_VEHICULO:
			buscarVehiculo();
			break;
		case BUSCAR_ALQUILER:
			buscarAlquiler();
			break;
		case MODIFICAR_CLIENTE:
			modificarCliente();
			break;
		case DEVOLVER_ALQUILER_CLIENTE:
			devolverAlquilerCliente();
			break;
		case DEVOLVER_ALQUILER_VEHICULO:
			devolverAlquilerVehiculo();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case BORRAR_VEHICULO:
			borrarVehiculo();
			break;
		case BORRAR_ALQUILER:
			borrarAlquiler();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_VEHICULOS:
			listarVehiculos();
			break;
		case LISTAR_ALQUILERES:
			listarAlquileres();
			break;
		case LISTAR_ALQUILERES_CLIENTE:
			listarAlquileresCliente();
			break;
		case LISTAR_ALQUILERES_VEHICULO:
			listarAlquileresVehiculo();
			break;
		case MOSTRAR_ESTADISTICAS_MENSUALES:
			MostrarEstadisticasMensualesTipoVehiculo();
			break;
		default:
			break;
		}
	}

	protected void insertarCliente() {
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
		System.out.println("Cliente introducido correctamente.");
		System.out.println("__________________________________");
	}

	protected void insertarVehiculo() {
		Consola.mostrarCabecera("Eligió insertar un vehículo.");
		try {
			controlador.insertar(Consola.leerVehiculo());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Vehículo introducido correctamente.");
		System.out.println("__________________________________:");
	}

	protected void insertarAlquiler() {
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
		System.out.println("Alquiler introducido correctamente.");
		System.out.println("__________________________________:");
	}

	protected void buscarCliente() {
		Consola.mostrarCabecera("Eligió buscar un cliente.");
		Cliente pepito = Consola.leerClienteDni();
		try {
			if (controlador.buscar(pepito) == null) {
				System.out.println("El cliente no se encuentra en la base de datos.");
			} else {
				System.out.println("Los datos del cliente son: " + pepito);
			}

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("__________________________________:");
	}

	protected void buscarVehiculo() {
		Consola.mostrarCabecera("Eligió buscar un turismo.");
		try {
			Vehiculo vehiculoMostrar = controlador.buscar(Consola.leerVehiculoMatricula());
			if (vehiculoMostrar == null) {
				System.out.println("El vehículo no se encuentra en la base de datos.");
			} else {
				System.out.println("El vehículo se encuentra en la base de datos.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("__________________________________:");
	}

	protected void buscarAlquiler() {
		Consola.mostrarCabecera("Eligió buscar un alquiler.");
		try {
			Alquiler alquilerDevolver = controlador.buscar(Consola.leerAlquiler());
			if (alquilerDevolver == null) {
				System.out.println("El alquiler no se encuentra en la base de datos.");
			} else {
				System.out.println("El alquiler es: " + alquilerDevolver);
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("La fecha no es así.");
		}
		System.out.println("__________________________________:");
	}

	protected void modificarCliente() {
		Consola.mostrarCabecera("Eligió modificar un cliente.");
		try {
			controlador.modificar(controlador.buscar(Consola.leerClienteDni()), Consola.leerNombre(),
					Consola.leerTelefono());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("__________________________________:");
	}

	protected void devolverAlquilerCliente() {
		Consola.mostrarCabecera("Eligió modificar un alquiler usando cliente.");
		try {
			controlador.devolver(controlador.buscar(Consola.leerClienteDni()), Consola.leerFechaDevolucion());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("La fecha no es así.");
		}
		System.out.println("__________________________________:");
	}

	protected void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Eligió modificar un alquiler usando cliente.");
		try {
			controlador.devolver(controlador.buscar(Consola.leerClienteDni()), Consola.leerFechaDevolucion());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("La fecha no es así.");
		}
		System.out.println("__________________________________:");
	}

	protected void borrarCliente() {
		Consola.mostrarCabecera("Eligió borrar un cliente.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerClienteDni()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("El cliente se ha borrado correctamente.");
		System.out.println("______________________________________:");
	}

	protected void borrarVehiculo() {
		Consola.mostrarCabecera("Eligió borrar un turismo.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerVehiculoMatricula()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("El vehículo se ha borrado correctamente.");
		System.out.println("_______________________________________:");
	}

	protected void borrarAlquiler() {
		Consola.mostrarCabecera("Eligió borrar un alquiler.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerAlquiler()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("La fecha no es así.");
		}
		System.out.println("El alquiler se ha borrado correctamente");
		System.out.println("______________________________________:");
	}

	protected void listarClientes() {
		Consola.mostrarCabecera("Eligió listar los clientes.");
		try {
			if (controlador.getClientes().size() != 0) {
				List<Cliente> listaCliente = controlador.getClientes();
				listaCliente.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
				for (Cliente cliente : controlador.getClientes()) {
					System.out.println(cliente);
				}

			} else {
				System.out.println("La lista está vacía.");
			}
			System.out.println("__________________________________:");

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void listarVehiculos() {
		Consola.mostrarCabecera("Eligió listar los turismos.");

		try {
			if (controlador.getVehiculoss().size() != 0) {
				List<Vehiculo> listaVehiculo = controlador.getVehiculoss();
				listaVehiculo.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
						.thenComparing(Vehiculo::getMatricula));
				for (Vehiculo turismo : controlador.getVehiculoss()) {
					System.out.println(turismo);
				}
			} else {
				System.out.println("La lista está vacía.");
			}
			System.out.println("__________________________________:");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * En este metodo y en los 2 siguientes falta por terminar el sort ya que como
	 * puedes ver ahi comparado estuve intentando hacer que me cogiera el getCliente
	 * para ordenar, pese a que era un método igual que getFechaAlquiler. Hubieron
	 * varios dias en los que intenté, juntando 2 comparatos... intentando hacer un
	 * comparable en el metodo en si. Sin embargo el tiempo me comió y decidí
	 * dejarlo comparando solo a un 50%. Con la retroalimentación lo arreglaré para
	 * la tarea 8.
	 * 
	 */
	protected void listarAlquileres() {
		Consola.mostrarCabecera("Eligió listar los alquileres.");
		try {

			if (controlador.getAlquileres().size() != 0) {
				List<Alquiler> listaAlquiler = controlador.getAlquileres();

				Comparator<Alquiler> comparado = Comparator.comparing(Alquiler::getFechaAlquiler);
				// Comparator<Alquiler> comparados = Comparator.comparing(Alquiler::getCliente);

				Collections.sort(listaAlquiler, comparado);

				for (Alquiler alquiler : controlador.getAlquileres()) {
					System.out.println(alquiler);
				}
				System.out.println("______________________________");
			} else {
				System.out.println("La lista está vacía.");
			}
			System.out.println("__________________________________:");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void listarAlquileresCliente() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un cliente.");
		Cliente pedro = Consola.leerClienteDni();
		try {
			if (controlador.getAlquileres(pedro).size() != 0) {
				List<Alquiler> listaAlquilerC = controlador.getAlquileres(pedro);
				listaAlquilerC.sort(Comparator.comparing(Alquiler::getFechaAlquiler));

				for (Alquiler alquiler : controlador.getAlquileres(pedro)) {
					System.out.println(alquiler);
				}
			} else {
				System.out.println("La lista del cliente está vacía.");
			}
			System.out.println("__________________________________:");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Eligió listar los alquileres de un turismo.");
		try {

			if (controlador.getAlquileres(Consola.leerVehiculoMatricula()).size() != 0) {
				List<Alquiler> listaAlquilerV = controlador.getAlquileres(Consola.leerVehiculoMatricula());
				listaAlquilerV.sort(Comparator.comparing(Alquiler::getFechaAlquiler));

				for (Alquiler alquiler : controlador.getAlquileres(Consola.leerVehiculoMatricula())) {
					System.out.println(alquiler);
				}
			} else {
				System.out.println("La lista del vehículo está vacía.");
			}
			System.out.println("__________________________________:");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		Map<TipoVehiculo, Integer> estadisticas = new EnumMap<>(TipoVehiculo.class);
		estadisticas.put(TipoVehiculo.AUTOBUS, 0);
		estadisticas.put(TipoVehiculo.FURGONETA, 0);
		estadisticas.put(TipoVehiculo.TURISMO, 0);

		return estadisticas;

	}

	public void MostrarEstadisticasMensualesTipoVehiculo() {
		Map<TipoVehiculo, Integer> estadisticasAMostrar = inicializarEstadisticas();
		Consola.mostrarCabecera("Eligió mostrar las estadísticas mensuales por vehículos:");
		Month mes = Consola.leerMes().getMonth();
		int turismo = 0;
		int autobus = 0;
		int furgoneta = 0;
		for (Alquiler alquiler : controlador.getAlquileres()) {
			if (alquiler.getFechaAlquiler().getMonth().equals(mes)) {
				if (alquiler.getVehiculo() instanceof Autobus) {
					autobus++;
				}
				if (alquiler.getVehiculo() instanceof Turismo) {
					turismo++;
				}
				if (alquiler.getVehiculo() instanceof Furgoneta) {
					furgoneta++;
				}
			}
		}
		estadisticasAMostrar.put(TipoVehiculo.AUTOBUS, autobus);
		estadisticasAMostrar.put(TipoVehiculo.TURISMO, turismo);
		estadisticasAMostrar.put(TipoVehiculo.FURGONETA, furgoneta);
		System.out.println(estadisticasAMostrar);
	}

}
