package cl.bonBonJovi.servicio;

import java.util.List;

import cl.bonBonJovi.modelo.Cliente;

public class ClienteServicio {
	private  List<Cliente> listaClientes;

	public ClienteServicio(List<Cliente> listaClientes) {

		this.listaClientes = listaClientes;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
	public void retornoListarClientes() { // Recorrer cada uno de los clientes
		
	}
	
	public void agregarCliente(Cliente cliente) {
		
	}
	
	public void editarCliente(Cliente cliente) {
		
	}
	
}
