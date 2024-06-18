package cl.bonBonJovi.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;

public class ArchivoServicio {

	
	
	public void importarDatosCsv(String rutaArchivo, String fileNameCsv, List<Cliente> listarClientes) {
		
		CategoriaEnum categoria = CategoriaEnum.SC;
		Cliente cliente;
		File file = new File(rutaArchivo +"/" + fileNameCsv+ ".csv");
	
		try (FileReader fl = new FileReader(file); BufferedReader bf = new BufferedReader(fl);){
			String linea;
			while((linea = bf.readLine())!= null) {
				String [] parte = linea.split(",");
	   
				if (parte[4].equalsIgnoreCase("Activo")) {
					categoria = CategoriaEnum.ACTIVO;
				}else if(parte[4].trim().equalsIgnoreCase("Inactivo"))  {
					categoria = CategoriaEnum.INACTIVO;
				}
		
				cliente = new Cliente(parte[0].trim(), parte[1].trim(), parte[2].trim(), parte[3] , categoria);
				listarClientes.add(cliente);
			}
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	
}
