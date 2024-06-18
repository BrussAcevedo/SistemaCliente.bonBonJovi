package cl.bonBonJovi.servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.utilidades.Utilidad;

public class ExportarTxt extends Exportador {

	@Override
	public void exportar(String ruta, String filename, List<Cliente> listaClientes) {
		List<Cliente> listaLectura;

		File file = new File(ruta + "/" + filename + ".txt");
		if (!file.exists()) {

			Utilidad.crearCarpeta(ruta);
			Utilidad.crearArchivoTxt(ruta, filename);
		}

		if (file.exists()) {

			listaLectura = RutasServicios.rutaToArraylist(ruta + "/" + filename + ".txt");
			listaClientes.addAll(listaLectura);

			try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
				for (Cliente cliente : listaClientes) {
					bw.write(cliente.toStringSimple());
					bw.newLine();
				}
				System.out.println("Datos Exportados Correctamente.");
				listaClientes.clear();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void leerDatosExp() {

	}

}
