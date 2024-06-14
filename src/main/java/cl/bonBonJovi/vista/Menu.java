package cl.bonBonJovi.vista;

import java.util.Scanner;

import cl.bonBonJovi.servicio.ArchivoServicio;
import cl.bonBonJovi.servicio.ClienteServicio;
import cl.bonBonJovi.servicio.ExportarCsv;
import cl.bonBonJovi.servicio.ExportarTxt;

public class Menu {

	private ClienteServicio clienteServicio;
	private ArchivoServicio archivoServicio;
	private ExportarCsv exportadorCsv;
	private ExportarTxt exportarTxt;

	private Scanner scan = new Scanner(System.in);
	private String fileName = "Clientes";
	private String fileName1 = "DBClientes.csv";
	
	
	public void menuPrincipal() {
		boolean estado = false;
		
		
		System.out.println("1. Listar Clientes\r\n"
				+ "2. Agregar Cliente\r\n"
				+ "3. Editar Cliente\r\n"
				+ "4. Cargar Datos\r\n"
				+ "5. Exportar Datos\r\n"
				+ "6. Salir\r\n"
				+ "Ingrese una opción:");
		String entradaStr = scan.nextLine();
		int entrada = Integer.parseInt(entradaStr);
		do{
			
			switch (entrada) {
			case 1:{
				listarCliente();
				break;
			}
			case 2:{
				agregarCliente();
				break;
			}
			case 3:{
				editarCliente();
				break;
			}
			case 4:{
				importarDatos();
				break;
			}
			case 5:{
				exportarDatos();
				break;
			}
			case 6:{
				estado = true;
				break;
			}
			
			default:
				System.out.println("Entrada No válida. Por favor, intente de nuevo.");
				break;
			}		
			
		}while(!estado);
		
		
		
		
		
		
	}

	public void listarCliente() {

	}

	public void agregarCliente() {

	}

	public void editarCliente() {

	}

	public void importarDatos() {

	}

	public void exportarDatos() {

	}

	public void terimnarPrograma() {

	}
}
