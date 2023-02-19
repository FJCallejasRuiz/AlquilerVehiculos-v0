package vista;

public enum Opcion {
	SALIR("Salir"), INSERTAR_CLIENTE("Insertar cliente"), INSERTAR_TURISMO("Insertar turismo"),
	INSERTAR_ALQUILER("Insertar alquiler"), BUSCAR_CLIENTE("Buscar cliente"), BUSCAR_TURISMO("Buscar turismo"),
	BUSCAR_ALQUILER("Buscar alquiler"), MODIFICAR_CLIENTE("Modificar cliente"), DEVOLVER_ALQUILER("Devolver alquiler"),
	BORRAR_CLIENTE("Borrar ciente"), BORRAR_TURISMO("Borrar Turismo"), BORRAR_ALQUILER("Borrar Alquiler"),
	LISTAR_CLIENTES("Listar clientes"), LISTAR_TURISMOS("Listar turismos"), LISTAR_ALQUILERES("Listar alquileres"),
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres cliente"), LISTAR_ALQUILERES_TURISMO("Listar alquileres turismo");

	public String texto;

	private Opcion(String texto) {
		this.texto = texto;
	}

	private boolean esOrdinalValido(int ordinal) {

		if (ordinal < 0 || ordinal > 16) {
			return false;
		} else {
			return true;
		}
	}

	public Opcion get(int ordinal) {

		if (esOrdinalValido(ordinal)) {
			switch (ordinal) {
			case 0:
				return Opcion.SALIR;
			case 1:
				return Opcion.INSERTAR_CLIENTE;
			case 2:
				return Opcion.INSERTAR_TURISMO;
			case 3:
				return Opcion.INSERTAR_ALQUILER;
			case 4:
				return Opcion.BUSCAR_CLIENTE;
			case 5:
				return Opcion.BUSCAR_TURISMO;
			case 6:
				return Opcion.BUSCAR_ALQUILER;
			case 7:
				return Opcion.MODIFICAR_CLIENTE;
			case 8:
				return Opcion.DEVOLVER_ALQUILER;
			case 9:
				return Opcion.BORRAR_CLIENTE;
			case 10:
				return Opcion.BORRAR_TURISMO;
			case 11:
				return Opcion.BORRAR_ALQUILER;
			case 12:
				return Opcion.LISTAR_CLIENTES;
			case 13:
				return Opcion.LISTAR_TURISMOS;
			case 14:
				return Opcion.LISTAR_ALQUILERES;
			case 15:
				return Opcion.LISTAR_ALQUILERES_CLIENTE;
			case 16:
				return Opcion.LISTAR_ALQUILERES_TURISMO;
			default:
				return Opcion.SALIR;
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