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
	
	public String agregarCliente(Cliente cliente) {
		if (cliente == null || cliente.getAniosCliente()== null || cliente.getApellidoCliente()==null || cliente.getNombreCategoria() ==null || cliente.getNombreCliente() == null || cliente.getRunCliente() == null) {
			return "Los datos del cliente no son correctos.";
		
		}else {
			listaClientes.add(cliente);
			return "Se ha agregado exitosamente el Cliente";
		}
		
	}
	
	public void editarCliente(Cliente cliente) {
		
	}
	
}
