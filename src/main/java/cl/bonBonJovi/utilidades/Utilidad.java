package cl.bonBonJovi.utilidades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utilidad {
	
	
	
	public static String borrarFilenameRuta(String rutaFilename) {
		
	
		String [] parte = rutaFilename.split("/");
		StringBuilder rutaActualizada = new StringBuilder();
		
		for (int i = 0; i< parte.length -1; i++) {
			rutaActualizada.append(parte[i]);
			if(i<parte.length -2) {
				rutaActualizada.append("/");
			}	
		} 
		return rutaActualizada.toString();
	}
	
	
	public static int validarEntero(String entrada, String regex) {
			int entradaInt= 0;
		if (entrada.matches(regex)) {
			entradaInt = Integer.parseInt(entrada);
			return entradaInt;
		}else {
			return entradaInt;
		}
	}
	

	public static String crearCarpeta(String ruta) {
		File fl = new File(ruta);

		if (!fl.exists()) {
			fl.mkdir();
			return "CarpetaCreada";
		} else {
			return "Error no se ha podido crear la carpeta";
		}

	}

	public static void crearArchivoCsv(String ruta, String nombreArchivo) {
		File carpeta = new File(ruta);
	
		File fl = new File(carpeta + "/" + nombreArchivo + ".csv");

		if (carpeta.exists()) {
			try {

				if (!fl.exists()) {
					fl.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void crearArchivoTxt(String ruta, String nombreArchivo) {
		File carpeta = new File(ruta);
		
		File fl = new File(carpeta + "/" + nombreArchivo + ".txt");

		if (carpeta.exists()) {
			
			System.out.println("flag");
			try {

				if (!fl.exists()) {
					fl.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void delay3000() {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	

	
	

}
