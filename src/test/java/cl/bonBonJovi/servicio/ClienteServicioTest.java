package cl.bonBonJovi.servicio;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.servicio.ClienteServicio;

public class ClienteServicioTest {
	
	public static Logger logger = Logger.getLogger("testing-unitario-ClienteServicio");
	
	private List<Cliente> listaClientesTest;
	private ClienteServicio clienteServicio;
	
	@BeforeEach
	public void inicializarArrayList() {
		listaClientesTest = new ArrayList<>();
		clienteServicio = new ClienteServicio(listaClientesTest);
	}
	
	@Test
	@DisplayName("Testing agregando cliente a ArrayList NO NULL")
	public void agregarClienteTest() {
		logger.info("Operación Agregar Cliente No nulo");
		Cliente clienteTest = new Cliente("11111111-1", "Pedro", "Quezada", "25 anios", CategoriaEnum.ACTIVO);
		Cliente clienteTest2 = new Cliente("11111112-1", "SPedro", "Quezada", "25 anios", CategoriaEnum.ACTIVO);
		
		clienteServicio.agregarCliente(clienteTest);
		clienteServicio.agregarCliente(clienteTest2);	
		
		assertTrue(listaClientesTest.contains(clienteTest), "El cliente no fue agregado a la lista");
	}
	
	@Test
	@DisplayName("Testing agregando cliente a ArrayList NULL")
	public void agregarClienteNullTest() {
		logger.info("Operación Agregar Cliente nulo");
		Cliente clienteNull = null;
		String resultado = clienteServicio.agregarCliente(clienteNull);
		
		assertEquals("Los datos del cliente no son correctos.",resultado);
		
	}

}
