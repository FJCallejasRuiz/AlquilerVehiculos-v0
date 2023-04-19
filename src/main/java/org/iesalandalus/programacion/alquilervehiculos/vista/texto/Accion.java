package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion {
	SALIR("Salir"), INSERTAR_CLIENTE("Insertar cliente"), INSERTAR_VEHICULO("Insertar vehículo"),
	INSERTAR_ALQUILER("Insertar alquiler"), BUSCAR_CLIENTE("Buscar cliente"), BUSCAR_VEHICULO("Buscar vehículo"),
	BUSCAR_ALQUILER("Buscar alquiler"), MODIFICAR_CLIENTE("Modificar cliente"), DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler por un cliente"),
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler por un vehículo"),BORRAR_CLIENTE("Borrar ciente"), BORRAR_VEHICULO("Borrar vehículo"), BORRAR_ALQUILER("Borrar Alquiler"),
	LISTAR_CLIENTES("Listar clientes"), LISTAR_VEHICULOS("Listar vehículos"), LISTAR_ALQUILERES("Listar alquileres"),
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres cliente"), LISTAR_ALQUILERES_VEHICULO("Listar alquileres vehículos"),MOSTRAR_ESTADISTICAS_MENSUALES("Muestra de estadísticas mensuales por tipo de vehículo");

	public String texto;

	private Accion(String texto) {
		this.texto = texto;
	}

	private static boolean esOrdinalValido(int ordinal) {

		if (ordinal < 0 || ordinal > 19) {
			return false;
		} else {
			return true;
		}
	}

	public static Accion get(int ordinal) {

		if (esOrdinalValido(ordinal)) {
			switch (ordinal) {
			case 0:
				return Accion.SALIR;
			case 1:
				return Accion.INSERTAR_CLIENTE;
			case 2:
				return Accion.INSERTAR_VEHICULO;
			case 3:
				return Accion.INSERTAR_ALQUILER;
			case 4:
				return Accion.BUSCAR_CLIENTE;
			case 5:
				return Accion.BUSCAR_VEHICULO;
			case 6:
				return Accion.BUSCAR_ALQUILER;
			case 7:
				return Accion.MODIFICAR_CLIENTE;
			case 8:
				return Accion.DEVOLVER_ALQUILER_CLIENTE;
			case 9:
				return Accion.DEVOLVER_ALQUILER_VEHICULO;
			case 10:
				return Accion.BORRAR_CLIENTE;
			case 11:
				return Accion.BORRAR_VEHICULO;
			case 12:
				return Accion.BORRAR_ALQUILER;
			case 13:
				return Accion.LISTAR_CLIENTES;
			case 14:
				return Accion.LISTAR_VEHICULOS;
			case 15:
				return Accion.LISTAR_ALQUILERES;
			case 16:
				return Accion.LISTAR_ALQUILERES_CLIENTE;
			case 17:
				return Accion.LISTAR_ALQUILERES_VEHICULO;
			case 18:
				return Accion.MOSTRAR_ESTADISTICAS_MENSUALES;
			default:
				return Accion.SALIR;
			}
		} else {
			throw new IllegalArgumentException("ERROR: Esa opción no existe");
		}
	}

	@Override
	public String toString() {
		return texto;

	}
}
//17