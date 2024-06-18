package cl.bonBonJovi.servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

		File file = new File(rutaArchivo + "\\" + fileNameCsv + ".txt");

		try (FileReader fl = new FileReader(file); BufferedReader bf = new BufferedReader(fl);) {
			String linea = "";
			while ((linea=bf.readLine()) != null) {
				
				listarClientes.add(linea);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void recorrerRutas() {
		
	}

}
