package cl.bonBonJovi.servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.utilidades.Utilidad;

public class RutasServicios {
	
	

	public static void añadirFilename(List<String> listaRutas, int index, String fileName) {

		List<String> rutasActualizadas = new ArrayList<>();

		for (int i = 0; i < listaRutas.size(); i++) {
			if (i == index) {
				rutasActualizadas.add(listaRutas.get(index) + "/" + fileName);
			} else {
				rutasActualizadas.add(listaRutas.get(i));
			}

		}

		listaRutas.clear();
		listaRutas.addAll(rutasActualizadas);
	}

	public static void exportarRutas(String ruta, String filename, List<String> listaRutas) {

		File file = new File(ruta + "/" + filename + ".txt");

		if (!file.exists()) {
			
			Utilidad.crearCarpeta(ruta);
			Utilidad.crearArchivoTxt(ruta, filename);
		}

		if (file.exists()) {

			try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
				for (String rutaExp : listaRutas) {
					bw.write(rutaExp);
					bw.newLine();
				}
				System.out.println("Rutas Exportadas Correctamente.");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void importarRutas(String rutaArchivo, String fileNameCsv, List<String> listarClientes) {

		File file = new File(rutaArchivo + "/" + fileNameCsv + ".txt");

		try (FileReader fl = new FileReader(file); BufferedReader bf = new BufferedReader(fl);) {
			String linea = "";
			while ((linea = bf.readLine()) != null) {

				listarClientes.add(linea);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String buscarEnRutasExp(String rut) {
		ArrayList<String> listaTempRutas = new ArrayList<>();
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		String ruta;
		File fl = new File("src/main/java/cl/bonBonJovi/archivos/rutas/rutasExport.txt");
		
		try (FileReader fr = new FileReader(fl); BufferedReader br = new BufferedReader(fr);) {
			while ((ruta = br.readLine()) != null) {
				listaTempRutas.add(ruta);
				listaClientes = rutaToArraylist(ruta);
				
				for (Cliente cliente : listaClientes) {
			
					if (cliente.getRunCliente().equals(rut)) {
						return ruta;
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "No se ha encontrado el rut.";
	}

	public static ArrayList<Cliente> rutaToArraylist(String ruta) {
		ArrayList<Cliente> clientesList = new ArrayList<>();
		clientesList.clear();
		CategoriaEnum categoria = CategoriaEnum.SC;

		File fl = new File(ruta);
		try (FileReader fr = new FileReader(fl); BufferedReader br = new BufferedReader(fr);) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parte = linea.split(",");
				
				if (parte[4].trim().toString().equalsIgnoreCase("Activo")) {
					categoria = CategoriaEnum.ACTIVO;
					
				} else if (parte[4].trim().toString().equalsIgnoreCase("Inactivo")) {
					categoria = CategoriaEnum.INACTIVO;
				}
				
				Cliente cliente = new Cliente(parte[0].trim(), parte[1].trim(), parte[2].trim(), parte[3].trim(), categoria);
				clientesList.add(cliente);
			}

			return clientesList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesList;

	}
	
	
	public static Cliente ArrayListToCliente(ArrayList<Cliente>listaCliente, String rut){
		Cliente clienteEncontrado = null;
		for (Cliente cliente : listaCliente) {
			if (cliente.getRunCliente().equals(rut)) {
				clienteEncontrado = cliente;
			}
		}
		return clienteEncontrado;
	}
	
	
	public static void archivoExpUpdate (String ruta, ArrayList<Cliente>listUpdate){
		ExportarTxt exportarTxt= new ExportarTxt();
		ExportarCsv exportarCsv= new ExportarCsv();
		File fl = new File(ruta);
	
		if (!listUpdate.isEmpty()&&fl.exists()) {

			if(ruta.contains("Clientes.txt")) {
				
				String rutaAct = Utilidad.borrarFilenameRuta(ruta);
				exportarTxt.exportar(rutaAct, "Clientes", listUpdate);
				
			}else if(ruta.contains("Clientes.csv")) {
				String rutaAct = Utilidad.borrarFilenameRuta(ruta);
				exportarCsv.exportar(rutaAct, "Clientes", listUpdate);
			}
		}else {
			System.out.println("Error: La lista esta Vacia.");
		}
	
	}
	
	public static ArrayList<Cliente> allArchivosToArrayList(List<String> rutas) {
		ArrayList<Cliente> allExportClientes = new ArrayList<>();
		allExportClientes.clear();
		CategoriaEnum categoria = CategoriaEnum.SC;
	
		for (String ruta: rutas) {
				
			File fl = new File(ruta);
			System.out.println();
			if(fl.exists()&& fl.isFile()) {
				
				try(FileReader fr = new FileReader(fl);BufferedReader br = new BufferedReader(fr);) {
					String linea;
					
					while((linea= br.readLine())!=null) {
						
						String[] parte = linea.split(",");
						
						if (parte[4].trim().toString().equalsIgnoreCase("Activo")) {
							categoria = CategoriaEnum.ACTIVO;
							
						} else if (parte[4].trim().toString().equalsIgnoreCase("Inactivo")) {
							categoria = CategoriaEnum.INACTIVO;
						}		
						
						Cliente cliente = new Cliente(parte[0].trim(), parte[1].trim(), parte[2].trim(), parte[3].trim(), categoria);
						allExportClientes.add(cliente);
					}		
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("Error: Nose encuentra la ruta o el archivo esta Vacío: "+ fl);
			}

		}
		
		return allExportClientes;
	}

		
		
		
		
	

}
