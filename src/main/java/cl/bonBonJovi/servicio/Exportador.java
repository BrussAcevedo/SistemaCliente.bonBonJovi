package cl.bonBonJovi.servicio;

import java.util.List;

import cl.bonBonJovi.modelo.Cliente;

public abstract class Exportador {
	
	public abstract void exportar(String filename, List<Cliente> listaclientes);
	
}
