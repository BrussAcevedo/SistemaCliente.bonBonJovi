package cl.bonBonJovi.main;

import java.util.ArrayList;
import java.util.List;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.servicio.ArchivoServicio;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		List<Cliente>listaNoExportada = new ArrayList<>();
		Cliente cliente = new Cliente("12341234-3", "aaaaaaaa", "asdasdddd", "25 Anios", CategoriaEnum.ACTIVO);
		Cliente cliente2 = new Cliente("12341234-2", "bbbbb", "asdasdas", "25 Anios", CategoriaEnum.INACTIVO);
		listaNoExportada.add(cliente);
		listaNoExportada.add(cliente2);
		
		ArchivoServicio archivoServicio = new ArchivoServicio();
		archivoServicio.importarDatosCsv("src/main/java/cl/bonBonJovi/archivosImportar", "DBClientes", listaNoExportada);
		System.out.println(listaNoExportada.toString());
	}

}
