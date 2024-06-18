package cl.bonBonJovi.vista;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.servicio.ArchivoServicio;
import cl.bonBonJovi.servicio.ClienteServicio;
import cl.bonBonJovi.servicio.ExportarCsv;
import cl.bonBonJovi.servicio.ExportarTxt;
import cl.bonBonJovi.servicio.RutasServicios;
import cl.bonBonJovi.utilidades.Utilidad;

public class Menu {

	private List<Cliente> listaNoExportada = new ArrayList<>();
	private List<Cliente> listaExportada = new ArrayList<>();
	private List<String> listaDeRutas = new ArrayList<>();

	private ClienteServicio clienteServNoExp = new ClienteServicio(listaNoExportada);
	private ClienteServicio clienteServExp = new ClienteServicio(listaExportada);
	private ClienteServicio clienteServExpTemp;
	
	private ArchivoServicio importarDatos = new ArchivoServicio();
	private MenuExportar rutasMenu = new MenuExportar();
	
	private ArchivoServicio leerDatosYaExportados = new ArchivoServicio();
	private ExportarCsv exportadorCsv;
	private ExportarTxt exportarTxt;
	private String rutaImport;
	private String rutaExport;

	private Scanner scan = new Scanner(System.in);
	private String fileName = "Clientes";
	private String fileName1 = "DBClientes.csv";

	public void menuPrincipal() {
		Cliente cliente1 = new Cliente("4655623", "juan", "Juanete", "5 Anios", CategoriaEnum.ACTIVO);
		Cliente cliente2 = new Cliente("46545545", "Carlos", "Carlete", "6 Anios", CategoriaEnum.INACTIVO);
		
		listaNoExportada.add(cliente1);
		listaNoExportada.add(cliente2);

		
		RutasServicios.importarRutas("src/main/java/cl/bonBonJovi/archivos/rutas", "rutasExport", listaDeRutas);
		
		boolean estado = false;

		do {
			System.out.println("1. Listar Clientes\r\n"

					+ "2. Agregar Cliente\r\n" 
					+ "3. Editar Cliente\r\n" 
					+ "4. Cargar Datos\r\n"
					+ "5. Exportar Datos\r\n" 
					+ "6. Salir\r\n" 
					+ "Ingrese una opción:");
			String entradaStr = scan.nextLine();
			int entrada = Integer.parseInt(entradaStr);

			switch (entrada) {
			case 1: {
				listarCliente();
				break;

			}
			case 2: {
				agregarCliente();
				break;

			}
			case 3: {
				editarCliente();
				break;

			}
			case 4: {
				importarDatos();
				break;

			}
			case 5: {
				exportarDatos();
				break;

			}
			case 6: {
				estado = terminarPrograma();
				break;
			}

			default:
				System.out.println("Entrada No válida. Por favor, intente de nuevo.");
				break;
			}

		} while (!estado);

	}

	public void listarCliente() {

		if (!listaExportada.isEmpty()) {
			System.out.println("Lista Guardada en datos Exportados:");
			clienteServExp.retornoListarClientes();
			System.out.println("----------------------------------------------");
		} else if (listaExportada.isEmpty()) {
			System.out.println("Datos Exportados: No hay datos.");
			System.out.println("----------------------------------------------");
		}
		if (!listaNoExportada.isEmpty()) {
			System.out.println("Lista de datos sin Exportar: ");
			clienteServNoExp.retornoListarClientes();
			System.out.println("----------------------------------------------");
		} else if (listaExportada.isEmpty()) {
			System.out.println("Datos sin Exportar: No hay datos.");
			System.out.println("----------------------------------------------");
			System.out.println();

		}
	}

	public void agregarCliente() {

		String rut = null;
		String nombre = null;
		String apellido = null;
		String anios = null;
		Cliente nuevoCliente = new Cliente(rut, nombre, apellido, anios, CategoriaEnum.SC);

		System.out.println("------------Agregar Nuevo Cliente---------------");

		System.out.println("Ingresa RUN del Cliente:");
		rut = scan.nextLine();
		nuevoCliente.setRunCliente(rut);
		System.out.println("Ingresa Nombre del Cliente:");
		nombre = scan.nextLine();
		nuevoCliente.setNombreCliente(nombre);
		System.out.println("Ingresa Apellido del Cliente:");
		apellido = scan.nextLine();
		nuevoCliente.setApellidoCliente(apellido);
		System.out.println("Ingresa años como Cliente: ");
		anios = scan.nextLine();
		nuevoCliente.setAniosCliente(anios);
		nuevoCliente.setNombreCategoria(CategoriaEnum.ACTIVO);

		System.out.println("Nota: los clientes nuevos ingresados tienen categoria de ACTIVO.");

		clienteServNoExp.agregarCliente(nuevoCliente);

		System.out.println();

	}

	public void editarCliente() {

		System.out.println("------------------Editar Clientes------------------");
		System.out.println("Seleccione una opcion: ");
		System.out.println(
				"1.-Cambiar el estado del Cliente\r\n" + "2.-Editar los datos ingresados del Cliente\r\n" + "");
		System.out.println("Ingrese su eleccion: ");
		String respuesta = scan.nextLine();
		if (respuesta.equals("1")) {
			editarClienteOpcionUno();
		} else if (respuesta.equals("2")) {
			editarClienteOpcionDos();
		}

	}

	public void editarClienteOpcionUno() {
		ArrayList<Cliente> listaTempExp = new ArrayList<>();
		
		Cliente clienteAEditar;
		String run;
		System.out.println("Ingrese el RUN del cliente: ");
		run = scan.nextLine();
		String rutaBusqueda = RutasServicios.buscarEnRutasExp(run);
		
		listaTempExp = RutasServicios.rutaToArraylist(rutaBusqueda);
		clienteServExpTemp = new ClienteServicio(listaTempExp);
		clienteAEditar = (clienteServExp.buscarDatosCliente(run));


		if (clienteAEditar == null) {

			clienteServExpTemp = new ClienteServicio(listaTempExp);

			clienteAEditar = (clienteServExpTemp.buscarDatosCliente(run));
			if (clienteAEditar == null) {
				System.out.println("No se encuentra el rut entregado.");
				return;
			}
		}

		System.out.println("-----Actualizando estado del Cliente-----");
		System.out.println("Nombre del cliente: " + clienteAEditar.getNombreCliente());
		System.out.println("El estado actual del cliente es: " + clienteAEditar.getNombreCategoria().getDescripcion());
		System.out.println("___");
		if (clienteAEditar.getNombreCategoria() == CategoriaEnum.ACTIVO) {
			System.out.println("1.-Si desea cambiar el estado del Cliente a Inactivo\r\n"
					+ "2.-Si desea mantener el estado del cliente Activo");
		} else if (clienteAEditar.getNombreCategoria() == CategoriaEnum.INACTIVO) {
			System.out.println("1.-Si desea mantener el estado del Cliente a Inactivo\r\n"
					+ "2.-Si desea cambiar el estado del cliente Activo");
		}

		String respuesta = scan.nextLine();

		if (respuesta.equals("1")) {
			clienteServExpTemp.editarCliente(run, 0, "Inactivo");
			RutasServicios.archivoExpUpdate(rutaBusqueda, listaTempExp);
			clienteServNoExp.editarCliente(run, 0, "Inactivo");
			
		} else if (respuesta.equals("2")) {
			clienteServExpTemp.editarCliente(run, 0, "Activo");
			RutasServicios.archivoExpUpdate(rutaBusqueda, listaTempExp);
			clienteServNoExp.editarCliente(run, 0, "Activo");
		} else {
			System.out.println("Dato no válido");
		}

		Cliente clienteEditado = clienteServExpTemp.buscarDatosCliente(run);
		if (clienteEditado != null) {
			System.out.println("-----------NuevosDatos-------------");
			System.out.println("Nombre: " + clienteEditado.getNombreCliente());
			System.out.println("RUN Cliente; " + clienteEditado.getRunCliente());
			System.out.println("Estado Actual: " + clienteEditado.getNombreCategoria());
		}
		Cliente clienteEditado2 = clienteServNoExp.buscarDatosCliente(run);
		if (clienteEditado2 != null) {
			System.out.println("-----------NuevosDatos-------------");
			System.out.println("Nombre: " + clienteEditado2.getNombreCliente());
			System.out.println("RUN Cliente; " + clienteEditado2.getRunCliente());
			System.out.println("Estado Actual: " + clienteEditado2.getNombreCategoria());
		}

	}

	public void editarClienteOpcionDos() {

		
		Cliente clienteAEditar;
		String run;
		System.out.println("Ingrese el RUN del cliente: ");
		run = scan.nextLine();

		ArrayList<Cliente> listaTempExp = new ArrayList<>();
		String rutaBusqueda = RutasServicios.buscarEnRutasExp(run);
		listaTempExp = RutasServicios.rutaToArraylist(rutaBusqueda);
		clienteServExpTemp = new ClienteServicio(listaTempExp);
		
		clienteAEditar = (clienteServExpTemp.buscarDatosCliente(run));

		if (clienteAEditar == null) {
			clienteAEditar = clienteServNoExp.buscarDatosCliente(run);

			if (clienteAEditar == null) {
				System.out.println("No se encuentra el rut entregado.");
				return;
			}
		}

		System.out.println("-----Actualizando estado del Cliente-----");
		System.out.println("1.- El RUN del cliente es: " + clienteAEditar.getRunCliente());
		System.out.println("2.- Nombre del cliente: " + clienteAEditar.getNombreCliente());
		System.out.println("3.- Apellido del cliente: " + clienteAEditar.getApellidoCliente());
		System.out.println("4.- Los años como cliente son: " + clienteAEditar.getAniosCliente());
		System.out.println("___");
		System.out.println();
		System.out.println("Ingrese opcion a editar de los datos del cliente: ");

		String respuesta = scan.nextLine();
		String nuevoDato;
		switch (respuesta) {
		case "1": {
			System.out.println("Ingrese el nuevo Run del cliente: ");
			nuevoDato = scan.nextLine();
			clienteServExpTemp.editarCliente(run, 1, nuevoDato);
			clienteServNoExp.editarCliente(run, 1, nuevoDato);

			Cliente clienteEditado = clienteServExpTemp.buscarDatosCliente(run);
			if (clienteEditado != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado.getNombreCliente());
				System.out.println("RUN Cliente; " + clienteEditado.getRunCliente());
				
			}
			Cliente clienteEditado2 = clienteServNoExp.buscarDatosCliente(run);
			if (clienteEditado2 != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado2.getNombreCliente());
				System.out.println("RUN Cliente; " + clienteEditado2.getRunCliente());
			}

			break;
		}

		case "2": {

			System.out.println("Ingrese el nuevo Nombre del cliente: ");
			nuevoDato = scan.nextLine();
			clienteServExpTemp.editarCliente(run, 2, nuevoDato);
			clienteServNoExp.editarCliente(run, 2, nuevoDato);

			Cliente clienteEditado = clienteServExpTemp.buscarDatosCliente(run);
			if (clienteEditado != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado.getNombreCliente());
				System.out.println("Apellido: " + clienteEditado.getApellidoCliente());
				System.out.println("RUN Cliente; " + clienteEditado.getRunCliente());

			}
			Cliente clienteEditado2 = clienteServNoExp.buscarDatosCliente(run);
			if (clienteEditado2 != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado2.getNombreCliente());
				System.out.println("Apellido: " + clienteEditado2.getApellidoCliente());
				System.out.println("RUN Cliente; " + clienteEditado2.getRunCliente());
			}

			break;
		}
		case "3": {

			System.out.println("Ingrese el nuevo Apellido del cliente: ");
			nuevoDato = scan.nextLine();
			clienteServExpTemp.editarCliente(run, 3, nuevoDato);
			clienteServNoExp.editarCliente(run, 3, nuevoDato);

			Cliente clienteEditado = clienteServExpTemp.buscarDatosCliente(run);
			if (clienteEditado != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado.getNombreCliente());
				System.out.println("Apellido Cliente: " + clienteEditado.getApellidoCliente());
				System.out.println("RUN Cliente; " + clienteEditado.getRunCliente());

			}
			Cliente clienteEditado2 = clienteServNoExp.buscarDatosCliente(run);
			if (clienteEditado2 != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado2.getNombreCliente());
				System.out.println("Apellido Cliente: " + clienteEditado2.getApellidoCliente());
				System.out.println("RUN Cliente; " + clienteEditado2.getRunCliente());
			}

			break;
		}
		case "4": {

			System.out.println("Ingrese el nuevo numero de años como cliente: ");
			nuevoDato = scan.nextLine();
			clienteServExpTemp.editarCliente(run, 4, nuevoDato);
			clienteServNoExp.editarCliente(run, 4, nuevoDato);

			Cliente clienteEditado = clienteServExpTemp.buscarDatosCliente(run);
			if (clienteEditado != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado.getNombreCliente());
				System.out.println("RUN Cliente; " + clienteEditado.getRunCliente());
				System.out.println("Anios de cliente: " + clienteEditado.getAniosCliente());

			}
			Cliente clienteEditado2 = clienteServNoExp.buscarDatosCliente(run);
			if (clienteEditado2 != null) {
				System.out.println("-----------NuevosDatos-------------");
				System.out.println("Nombre: " + clienteEditado2.getNombreCliente());
				System.out.println("RUN Cliente; " + clienteEditado2.getRunCliente());
				System.out.println("Anios de cliente: " + clienteEditado2.getAniosCliente());
			}

			break;
		}
		default: {
			System.out.println("Dato ingresado no válido.");
			break;
		}

		}
		System.out.println();
		System.out.println();
		System.out.println("Volviendo al menu Principal...");
		Utilidad.delay3000();
	}

	public void importarDatos() {
		System.out.println("Selecciona la carpeta que contiene los datos a importar: ");
		rutaImport = "src/main/java/cl/bonBonJovi/archivosImportar";
		String nombreArchivo = "DBClientes";
		System.out.println("1- Ruta por Default: " + rutaImport + nombreArchivo + ".csv");
		String respuesta = scan.nextLine();

		if (respuesta.equals("1")) {
			importarDatos.importarDatosCsv(rutaImport, nombreArchivo, listaNoExportada);
			System.out.println(listaNoExportada.toString());
		} else {
			System.out.println("Opcion no válida. Volviendo al menu Principal...");
			Utilidad.delay3000();
		}

	}

	public void exportarDatos() {
		rutasMenu.menuRutas(listaDeRutas, listaNoExportada);
	}

	public boolean terminarPrograma() {
		
		boolean estado = true;
		System.out.println("Cerrando Programa...");
		Utilidad.delay3000();
		scan.close();
		System.out.println("Programa Finalizado.");
		return estado;
	}
}
