package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FactoriaFuenteDatos {
	MEMORIA {
		@Override
		public IFuenteDatos crear() {

			FuenteDatosMemoria fuente = new FuenteDatosMemoria();
			return fuente;
		}
	};

	public abstract IFuenteDatos crear();

}