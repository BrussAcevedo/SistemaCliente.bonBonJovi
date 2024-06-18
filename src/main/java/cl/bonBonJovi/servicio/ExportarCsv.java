package cl.bonBonJovi.servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.utilidades.Utilidad;

public class ExportarCsv extends Exportador {

	@Override

	public void exportar(String ruta, String filename, List<Cliente> listaClientes) {
		File file = new File(ruta + "/" + filename );

		if (!file.exists()) {
			Utilidad.crearCarpeta(ruta);
			Utilidad.crearArchivoCsv(ruta, filename);
		}

		if (file.exists()) {

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
}
