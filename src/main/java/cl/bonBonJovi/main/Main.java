package cl.bonBonJovi.main;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cl.bonBonJovi.modelo.CategoriaEnum;
import cl.bonBonJovi.modelo.Cliente;
import cl.bonBonJovi.servicio.ExportarCsv;
import cl.bonBonJovi.servicio.ExportarTxt;
import cl.bonBonJovi.servicio.RutasServicios;
import cl.bonBonJovi.utilidades.Utilidad;
import cl.bonBonJovi.vista.Menu;
import cl.bonBonJovi.vista.MenuExportar;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Menu menu = new Menu();
		menu.menuPrincipal();

		
	}

}
