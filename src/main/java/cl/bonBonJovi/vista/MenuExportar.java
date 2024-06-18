package cl.bonBonJovi.vista;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.servicio.ExportarCsv;
import cl.bonBonJovi.servicio.ExportarTxt;
import cl.bonBonJovi.servicio.RutasServicios;
import cl.bonBonJovi.utilidades.Utilidad;

public class MenuExportar {
	
	private Scanner scan = new Scanner(System.in);
	private ExportarCsv exportarCsv = new ExportarCsv();
	private ExportarTxt exportarTxt = new ExportarTxt();


	
	public void menuRutas(List<String> listaDeRutas, List<Cliente>clientePorExport){
		
		
		
		if(listaDeRutas.isEmpty()) {
			listaDeRutas.add("src/main/java/cl/bonBonJovi/archivos/Exportados/Clientes.txt");
			RutasServicios.exportarRutas("src/main/java/cl/bonBonJovi/archivos/rutas", "rutasExport", listaDeRutas);
		}
		
		
		do {
			System.out.println("----------Exportar Datos----------");
			System.out.println("1- Exportar en ruta Existentes. ");
			System.out.println("2- Crear nueva ruta. ");
			System.out.println("(Nota:Si seleecionas opcion 2 solo se crea la ruta. Para exportar datos, volver a ingresar a el menu y buscar la nueva ruta en rutas existentes.)");
			System.out.println("Ingrese una opcion: ");
			String resultadoRuta = scan.nextLine();
			if (resultadoRuta.equals("1")) { // Rutas existentes
				if(!listaDeRutas.isEmpty()) {
				
					File flExport ;
					
					System.out.println("--------------Rutas Existentes-------------");
					for (int i=0; i< listaDeRutas.size(); i++) {
						String ruta = listaDeRutas.get(i);
						flExport = new File(ruta);
						
							System.out.println(i+".-"+ruta);
							
					}

					System.out.println();
					System.out.println("(Advertencia: los datos ingresados sobreescribiran el archivo).");
					System.out.println("Si desea volver al menu principal escriba "+"\"Menu\""+".");
					System.out.println("Seleccione la ruta del archivo a exportar");
					
					String rutaSelect = scan.nextLine();
					int rutaSelectInt = Utilidad.validarEntero(rutaSelect, "\\d+");
					
					if(rutaSelectInt <= listaDeRutas.size()) {
						System.out.println("-------------- Formato de salida Archivo Exportacion------------");
						System.out.println("Seleccione el formato del arcchivo");
						System.out.println("1- Clientes.txt");
						System.out.println("2- Clientes.csv");
						String resultadoFormato = scan.nextLine();
						
						int resultadoFormatoInt = Utilidad.validarEntero(resultadoFormato, "\\d++");
						String nombreArchivoTxt = "Clientes.txt";
						String nombreArchivoCsv = "Clientes.csv";
						
						if (resultadoFormatoInt == 1) {
							
							if((!listaDeRutas.get(rutaSelectInt).contains(nombreArchivoTxt))&&(!listaDeRutas.get(rutaSelectInt).contains(nombreArchivoCsv))) {
								RutasServicios.añadirFilename(listaDeRutas, rutaSelectInt, "Clientes.txt");								
							}else if(listaDeRutas.get(rutaSelectInt).contains(nombreArchivoCsv)&& !listaDeRutas.toString().contains(nombreArchivoTxt)) {
								
								String rutaSinFilename = Utilidad.borrarFilenameRuta(listaDeRutas.get(rutaSelectInt));
								System.out.println(rutaSinFilename);
								listaDeRutas.add(rutaSinFilename+"/"+"Clientes.txt");
							}else{
								System.out.println("Aviso: El archivo existia previamente, los datos se han sobreescrito.");
							}
							String rutaFormatoExport= Utilidad.borrarFilenameRuta(listaDeRutas.get(rutaSelectInt));
							exportarTxt.exportar(rutaFormatoExport, "Clientes", clientePorExport);
							
							
						}else if(resultadoFormatoInt == 2) {
							
							if((!listaDeRutas.get(rutaSelectInt).contains(nombreArchivoTxt))&&(!listaDeRutas.get(rutaSelectInt).contains(nombreArchivoCsv))) {
								RutasServicios.añadirFilename(listaDeRutas, rutaSelectInt, "Clientes.csv");
								
							}else if(listaDeRutas.get(rutaSelectInt).contains(nombreArchivoTxt)&& !listaDeRutas.toString().contains(nombreArchivoCsv)) {
								System.out.println("HOLA");
								String rutaSinFilename = Utilidad.borrarFilenameRuta(listaDeRutas.get(rutaSelectInt));
								System.out.println(rutaSinFilename);
								listaDeRutas.add(rutaSinFilename+"/"+"Clientes");
							}else {
								System.out.println("Aviso: El archivo existia previamente, los datos se han sobreescrito.");
							}
							String rutaFormatoExport= Utilidad.borrarFilenameRuta(listaDeRutas.get(rutaSelectInt));
							exportarCsv.exportar(rutaFormatoExport, "Clientes", clientePorExport);
						}else {
							System.out.println("Formato no Válido.");
							break;
						}
						
						
					}
					
					RutasServicios.exportarRutas("src/main/java/cl/bonBonJovi/archivos/rutas", "rutasExport", listaDeRutas);
					break;
					
				}else {
					System.out.println("No existen rutas existentes creadas");
				}
							
			}
			else if(resultadoRuta.equals("2")) {
				
					System.out.println("Ingresa la ruta para el archivo Clientes(.txt o .csv) de export: ");
					String nuevaRuta = scan.nextLine();
					File flBusqueda = new File(nuevaRuta);
					if(flBusqueda.exists()) {
						listaDeRutas.add(nuevaRuta);
						System.out.println("Ruta Agregada Correctamente.");
						
					}else {
						System.out.println("La ruta ingresada no Existe.");
					}
					
						
					}	
		
		}while(true);
	}
	

}
