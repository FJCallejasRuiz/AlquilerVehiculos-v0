package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FactoriaFuenteDatos {
	MEMORIA {
		@Override
		 IFuenteDatos crear() {

			FuenteDatosMemoria fuente = new FuenteDatosMemoria();
			return fuente;
		}
	},
	FICHEROS {
		@Override
		 IFuenteDatos crear() {

			FuenteDatosFicheros fuente = new FuenteDatosFicheros();
			return fuente;
		}
	};

	 abstract IFuenteDatos crear();

}