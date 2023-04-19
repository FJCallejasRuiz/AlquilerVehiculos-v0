package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

public class Clientes implements IClientes {
	private final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\datos\\clientes.xml";
	private final String RAIZ = "Clientes";
	private final String CLIENTE = "Cliente";
	private final String NOMBRE = "Nombre";
	private final String DNI = "Dni";
	private final String TELEFONO = "Telefono";

	List<Cliente> coleccionClientes;
	private static Clientes instancia;

	private Clientes() throws OperationNotSupportedException {
		coleccionClientes = new ArrayList<Cliente>();
		comenzar();
	}

	static Clientes getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Clientes();
		}
		return instancia;
	}

	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}

	private void leerXml() throws OperationNotSupportedException {
		Document documento = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList listaClientes;
		Element cliente;

		listaClientes = documento.getElementsByTagName(CLIENTE);

		for (int i = 0; i < listaClientes.getLength(); i++) {
			cliente = (Element) listaClientes.item(i);
			
			insertar(elementToCliente(cliente));

		}
	}

	private Cliente elementToCliente(Element elemento) {
		String dni;
		dni = elemento.getAttribute(DNI);
		String nombre;
		nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
		String telefono;
		telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();

		Cliente cliente = new Cliente(nombre, dni, telefono);

		return cliente;

	}

	public void terminar() {
		escribirXml();
	}

	private void escribirXml() {

		Document prueba = UtilidadesXml.crearDomVacio(RAIZ);

		Element raiz = prueba.getDocumentElement();

		for (Cliente cliente : coleccionClientes) {
			raiz.appendChild(clienteToElement(prueba, cliente));

		}
		UtilidadesXml.domToXml(prueba, CLIENTE);
	}

	private Element clienteToElement(Document doc, Cliente cliente) {
		Element eleCliente = doc.createElement(CLIENTE);

		Attr atriDni = doc.createAttribute(DNI);
		eleCliente.setAttributeNode(atriDni);

		Element eleNombre = doc.createElement(NOMBRE);
		eleNombre.appendChild(doc.createTextNode(cliente.getNombre()));
		eleCliente.appendChild(eleNombre);

		Element eleTelefono = doc.createElement(TELEFONO);
		eleTelefono.appendChild(doc.createTextNode(cliente.getTelefono()));
		eleCliente.appendChild(eleTelefono);

		return eleCliente;

	}

	@Override
	public List<Cliente> get() {

		List<Cliente> coleccionTemporal = new ArrayList<Cliente>();
		for (Cliente cliente : coleccionClientes) {
			coleccionTemporal.add(cliente);
		}
		return coleccionTemporal;
	}

	@Override
	public int getCantidad() {
		int cantidad = coleccionClientes.size();

		return cantidad;
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		} else if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	@Override
	public Cliente buscar(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			int index = coleccionClientes.indexOf(cliente);
			return coleccionClientes.get(index);
		} else {
			return null;
		}
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		} else if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");

		} else if (coleccionClientes.contains(cliente)) {

			int index = coleccionClientes.indexOf(cliente);
			if (nombre != null) {
				coleccionClientes.get(index).setNombre(nombre);
			}
			if (telefono != null) {
				coleccionClientes.get(index).setTelefono(telefono);
			}

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

	}
}