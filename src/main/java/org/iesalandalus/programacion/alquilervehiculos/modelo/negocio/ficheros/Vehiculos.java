package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Vehiculos implements IVehiculos {

	private final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\datos\\vehiculos.xml";
	private final String RAIZ = "Vehiculos";
	private final String VEHICULO = "Vehiculo";
	private final String MARCA = "Marca";
	private final String MODELO = "Modelo";
	private final String MATRICULA = "Matricula";
	private final String CILINDRADA = "Cilindrada";
	private final String PLAZAS = "Plazas";
	private final String PMA = "Pma";
	private final String TIPO = "Tipo";
	private final String TURISMO = "Turismo";
	private final String AUTOBUS = "Autobus";
	private final String FURGONETA = "Furgoneta";

	List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia;

	private Vehiculos() throws OperationNotSupportedException {
		coleccionVehiculos = new ArrayList<Vehiculo>();
		comenzar();
	}

	static Vehiculos getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Vehiculos();
		}
		return instancia;

	}

	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}

	private void leerXml() throws OperationNotSupportedException {
		Document documento = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList listaVehiculos;
		Element vehiculo;

		listaVehiculos = documento.getElementsByTagName(VEHICULO);

		for (int i = 0; i < listaVehiculos.getLength(); i++) {
			vehiculo = (Element) listaVehiculos.item(i);

			insertar(elementToVehiculo(vehiculo));

		}	
	}

	private Vehiculo elementToVehiculo(Element elemento) {

		Vehiculo vehiculoFinal = null;
		String matricula;
		matricula = elemento.getAttribute(MATRICULA);
		String tipo;
		tipo = elemento.getAttribute(TIPO);
		String marca;
		marca = elemento.getElementsByTagName(MARCA).item(0).getTextContent();
		String modelo;
		modelo = elemento.getElementsByTagName(MODELO).item(0).getTextContent();

		if (tipo == "Turismo") {
			String cilindrada;
			cilindrada = elemento.getElementsByTagName(CILINDRADA).item(0).getTextContent();
			int cilindradaFinal = Integer.parseInt(cilindrada);

			return vehiculoFinal = new Turismo(marca, modelo, cilindradaFinal, matricula);

		} else if (tipo == "Autobus") {
			String plazas;
			plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			int plazasFinal = Integer.parseInt(plazas);
			return vehiculoFinal = new Autobus(marca, modelo, plazasFinal, matricula);

		} else if (tipo == "Furgoneta") {
			String plazas;
			plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			int plazasFinal = Integer.parseInt(plazas);
			String pma;
			pma = elemento.getElementsByTagName(PMA).item(0).getTextContent();
			int pmaFinal = Integer.parseInt(pma);
			return vehiculoFinal = new Furgoneta(marca, modelo, pmaFinal, plazasFinal, matricula);
		} else

			return vehiculoFinal;

	}

	public void terminar() {
		escribirXml();
	}

	private void escribirXml() {

		Document prueba = UtilidadesXml.crearDomVacio(RAIZ);

		Element raiz = prueba.getDocumentElement();

		for (Vehiculo vehiculo : coleccionVehiculos) {
			raiz.appendChild(vehiculoToElement(prueba, vehiculo));

		}
		UtilidadesXml.domToXml(prueba, VEHICULO);
	}

	private Element vehiculoToElement(Document doc, Vehiculo vehiculo) {
		Element eleVehiculo = doc.createElement(VEHICULO);

		Attr atriMatricula = doc.createAttribute(MATRICULA);
		eleVehiculo.setAttributeNode(atriMatricula);

		Attr atriTipo = doc.createAttribute(TIPO);
		eleVehiculo.setAttributeNode(atriTipo);

		Element eleMarca = doc.createElement(MARCA);
		eleMarca.appendChild(doc.createTextNode(vehiculo.getMarca()));
		eleVehiculo.appendChild(eleMarca);

		Element eleModelo = doc.createElement(MODELO);
		eleModelo.appendChild(doc.createTextNode(vehiculo.getModelo()));
		eleVehiculo.appendChild(eleModelo);

		if (vehiculo instanceof Turismo) {

			Element eleTurismo = doc.createElement(TURISMO);

			Element eleCilindrada = doc.createElement(CILINDRADA);
			eleCilindrada.appendChild(doc.createTextNode(Integer.toString(((Turismo) eleTurismo).getCilindrada())));
			eleTurismo.appendChild(eleCilindrada);
			eleVehiculo.appendChild(eleTurismo);

		} else if (vehiculo instanceof Autobus) {
			Element eleAutobus = doc.createElement(AUTOBUS);

			Element elePlazas = doc.createElement(PLAZAS);
			elePlazas.appendChild(doc.createTextNode(Integer.toString(((Autobus) eleAutobus).getPlazas())));
			eleAutobus.appendChild(elePlazas);
			eleVehiculo.appendChild(eleAutobus);

		} else if (vehiculo instanceof Furgoneta) {
			Element eleFurgoneta = doc.createElement(FURGONETA);

			Element elePlazas = doc.createElement(PLAZAS);
			elePlazas.appendChild(doc.createTextNode(Integer.toString(((Furgoneta) eleFurgoneta).getPlazas())));

			Element elePma = doc.createElement(PMA);
			elePma.appendChild(doc.createTextNode(Integer.toString(((Furgoneta) eleFurgoneta).getPma())));

			eleFurgoneta.appendChild(elePlazas);
			eleFurgoneta.appendChild(elePma);
			eleVehiculo.appendChild(eleFurgoneta);
		}

		return eleVehiculo;

	}

	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> coleccionTemporal = new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : coleccionVehiculos) {
			coleccionTemporal.add(vehiculo);
		}
		return coleccionTemporal;
	}

	@Override
	public int getCantidad() {
		int cantidad = coleccionVehiculos.size();

		return cantidad;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		} else 
		if (!coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.add(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");
		}
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
		} else if (coleccionVehiculos.contains(vehiculo)) {
			int index = coleccionVehiculos.indexOf(vehiculo);
			return coleccionVehiculos.get(index);
		} else {
			return null;
		}
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		} else if (coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.remove(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
		}
	}

}
