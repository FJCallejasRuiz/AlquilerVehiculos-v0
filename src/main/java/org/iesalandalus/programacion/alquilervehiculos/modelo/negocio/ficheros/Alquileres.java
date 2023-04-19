package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {
	private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\datos\\vehiculos.xml";
	private final String RAIZ = "Alquileres";
	private final String ALQUILER = "Alquiler";
	private final String DNI_CLIENTE = "Dni";
	private final String MATRICULA_VEHICULO = "Matricula";
	private final String FECHA_ALQUILER = "FechaAlquiler";
	private final String FECHA_DEVOLUCION = "FechaDevolucion";
	private final String FORMATO = "Formato";

	List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;

	private Alquileres() throws OperationNotSupportedException {
		coleccionAlquileres = new ArrayList<Alquiler>();
		comenzar();
	}

	static Alquileres getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Alquileres();
		}
		return instancia;

	}

	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}

	private void leerXml() throws OperationNotSupportedException {
		Document documento = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList listaAlquileres;
		Element alquiler;

		listaAlquileres = documento.getElementsByTagName(ALQUILER);

		for (int i = 0; i < listaAlquileres.getLength(); i++) {
			alquiler = (Element) listaAlquileres.item(i);

			insertar(elementToAlquiler(alquiler));

		}
	}

	private Alquiler elementToAlquiler(Element elemento) throws OperationNotSupportedException {
		String dni;
		dni = elemento.getAttribute(DNI_CLIENTE);
		Cliente pruebaC = new Cliente("Bob Esponja", dni, "693310060");
		Cliente buscarC = Clientes.getInstancia().buscar(pruebaC);

		String matricula;
		matricula = elemento.getAttribute(MATRICULA_VEHICULO);
		Vehiculo pruebaV = new Turismo("BMW", "A", 500, matricula);
		Vehiculo buscarV = Vehiculos.getInstancia().buscar(pruebaV);

		String fechaAlquiler;
		fechaAlquiler = elemento.getElementsByTagName(FECHA_ALQUILER).item(0).getTextContent();
		LocalDate fechaAlquilerTrans = LocalDate.parse(fechaAlquiler, FORMATO_FECHA);

		Alquiler alquilerNoDev = new Alquiler(buscarC, buscarV, fechaAlquilerTrans);

		String fechaDevolucion;
		if (elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0).getTextContent() != null) {
			fechaDevolucion = elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0).getTextContent();
			LocalDate fechaDevolucionTrans = LocalDate.parse(fechaDevolucion, FORMATO_FECHA);
			alquilerNoDev.devolver(fechaDevolucionTrans);
		}

		return alquilerNoDev;

	}

	public void terminar() {
		escribirXml();
	}

	private void escribirXml() {

		Document prueba = UtilidadesXml.crearDomVacio(RAIZ);

		Element raiz = prueba.getDocumentElement();

		for (Alquiler alquiler : coleccionAlquileres) {
			raiz.appendChild(alquilerToElement(prueba, alquiler));

		}
		UtilidadesXml.domToXml(prueba, ALQUILER);
	}

	private Element alquilerToElement(Document doc, Alquiler alquiler) {
		Element eleAlquiler = doc.createElement(ALQUILER);

		Attr atriDni = doc.createAttribute(DNI_CLIENTE);
		eleAlquiler.setAttributeNode(atriDni);

		Attr atriMatricula = doc.createAttribute(MATRICULA_VEHICULO);
		eleAlquiler.setAttributeNode(atriMatricula);

		Element eleFecha = doc.createElement(FECHA_ALQUILER);
		eleFecha.appendChild(doc.createTextNode(alquiler.getFechaAlquiler().format(FORMATO_FECHA)));
		Attr atriFormato = doc.createAttribute(FORMATO);
		eleFecha.setAttributeNode(atriFormato);
		eleAlquiler.appendChild(eleFecha);

		Element eleFechaD = doc.createElement(FECHA_ALQUILER);
		eleFechaD.appendChild(doc.createTextNode(alquiler.getFechaDevolucion().format(FORMATO_FECHA)));
		Attr atriFormatoD = doc.createAttribute(FORMATO);
		eleFechaD.setAttributeNode(atriFormatoD);
		eleAlquiler.appendChild(eleFechaD);

		return eleAlquiler;

	}

	@Override
	public List<Alquiler> get() {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();
		for (Alquiler alquiler : coleccionAlquileres) {
			coleccionTemporal.add(alquiler);
		}
		return coleccionTemporal;
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente))
				coleccionTemporal.add(alquiler);
		}
		return coleccionTemporal;
	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> coleccionTemporal = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo))
				coleccionTemporal.add(alquiler);
		}
		return coleccionTemporal;
	}

	@Override
	public int getCantidad() {
		int cantidad = coleccionAlquileres.size();

		return cantidad;
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			} else if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			} else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
			} else if (alquiler.getVehiculo().equals(vehiculo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			}
		}

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver una fecha de devolución nula.");
		}
		if (getAlquilerAbierto(cliente).equals(null)) {
			throw new OperationNotSupportedException("ERROR: No hay alquiler a devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(cliente));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);
		} else if (index == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler con vehículo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver una fecha de devolución nula.");
		}
		if (getAlquilerAbierto(vehiculo).equals(null)) {
			throw new OperationNotSupportedException("ERROR: No hay alquiler a devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(vehiculo));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);
		} else if (index == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		} else if (coleccionAlquileres.contains(alquiler)) {
			int index = coleccionAlquileres.indexOf(alquiler);
			return coleccionAlquileres.get(index);
		} else {
			return null;
		}
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) throws OperationNotSupportedException {
		List<Alquiler> listaAlquiler = get(cliente);
		Alquiler alquilerDevuelto = null;

		for (Alquiler alquiler : listaAlquiler) {
			if (alquiler.getFechaDevolucion().equals(null)) {
				alquilerDevuelto = alquiler;
			}
		}
		return alquilerDevuelto;
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
		List<Alquiler> listaAlquiler = get(vehiculo);
		Alquiler alquilerDevuelto = null;

		for (Alquiler alquiler : listaAlquiler) {
			if (alquiler.getFechaDevolucion().equals(null)) {
				alquilerDevuelto = alquiler;
			}
		}
		return alquilerDevuelto;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		} else if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}
}
