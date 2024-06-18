package cl.bonBonJovi.servicio;

import java.util.List;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;

public class ClienteServicio {
	private List<Cliente> lista;

	public ClienteServicio(List<Cliente> lista) {
		this.lista = lista;
	}

	// Fin Getters setters y constructor.

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public void retornoListarClientes() { // Recorrer cada uno de los clientes
		String ruta = null;
		
		if (!lista.isEmpty()) {
			for (Cliente cliente : this.lista) {
				System.out.println();
				System.out.println("------------------Datos Cliente------------------");
				System.out.println("RUN del Cliente: " + cliente.getRunCliente());
				System.out.println("Nombre del Cliente:" + cliente.getNombreCliente());
				System.out.println("Apellido del Cliente: " + cliente.getApellidoCliente());
				System.out.println("Años como Cliente: " + cliente.getAniosCliente());
				System.out.println("Categoría del Cliente: " + cliente.getNombreCategoria());
				ruta = RutasServicios.buscarEnRutasExp(cliente.getRunCliente());
				if (ruta != null) {
					System.out.println("Ruta: " + ruta);
				}
				System.out.println();
			}

		}else {
			System.out.println("Error la lista esta Vacía");
		}
	}

	public String agregarCliente(Cliente cliente) {

		if (cliente == null || cliente.getAniosCliente() == null || cliente.getApellidoCliente() == null
				|| cliente.getNombreCategoria() == null || cliente.getNombreCliente() == null
				|| cliente.getRunCliente() == null) {

			return "Los datos del cliente no son correctos.";

		} else {
			lista.add(cliente);
			return "Se ha agregado exitosamente el Cliente";
		}

	}

	public Cliente buscarDatosCliente(String rut) {

		Cliente clienteEncontrado = null;
		for (Cliente cliente : this.lista) {
			if (cliente.getRunCliente().equals(rut)) {
				clienteEncontrado = cliente;
				return clienteEncontrado;
			}
		}
		return clienteEncontrado;
	}

	public void editarCliente(String rutCliente, int tipoDato, String nuevoDato) {

		switch (tipoDato) {
		case 0: {
			if (nuevoDato.equals("Activo")) {

				lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
						.forEach(cliente -> cliente.setNombreCategoria(CategoriaEnum.ACTIVO));

			} else if (nuevoDato.equals("Inactivo")) {

				lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
						.forEach(cliente -> cliente.setNombreCategoria(CategoriaEnum.INACTIVO));

			}

			break;
		}

		case 1: { // RUT

			lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
					.forEach(cliente -> cliente.setRunCliente(nuevoDato));

			break;
		}
		case 2: { // nombre

			lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
					.forEach(cliente -> cliente.setNombreCliente(nuevoDato));

			break;
		}
		case 3: { // Apellido
			lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
					.forEach(cliente -> cliente.setApellidoCliente(nuevoDato));

			break;
		}
		case 4: { // numero Anios cliente
			lista.stream().filter(cliente -> cliente.getRunCliente().equals(rutCliente))
					.forEach(cliente -> cliente.setAniosCliente(nuevoDato));
			break;
		}
		default: {
			System.out.println("Error: entrada Switch editar Cliente no válido");
			break;
		}

		}

	}
}
