package cl.bonBonJovi.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.bonBonJovi.modelo.Cliente;

public class ArchivoServicioTest {

	
	public static Logger logger = Logger.getLogger("testing-unitario-ArchivoServicio");
	
			List <Cliente> listaTest;
			ArchivoServicio archivoServicio;
			
	@BeforeEach
	public void iniciarArray() {
		listaTest = new ArrayList<>();
		archivoServicio = new ArchivoServicio();
	}
	
	@DisplayName("Test de importacion desde Csv a arrayList de clase Cliente")
	@Test
	public void importTest() {
		logger.info("Testing metodo importar datos Csv de ArchivoServicio");
		String rutaArchivo = "src\\main\\java\\cl\\bonBonJovi\\archivos\\importacion";
		String nombreArchivoCsv= "DBClientes";
		archivoServicio.importarDatosCsv(rutaArchivo , nombreArchivoCsv, listaTest);
		
		for(Cliente cliente:listaTest) {
			System.out.println(cliente);
		}
		
	}
	
}
