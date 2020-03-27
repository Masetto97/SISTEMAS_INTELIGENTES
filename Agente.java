package sistemasInteligentes;

import java.io.FileNotFoundException;
/*************************************************************************************************************************
 * NOMBRE CLASE:  Agente
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Clase encargada de crear el nuevo terreno, ya sea desde un archivo o desde teclado; tambien se encarga de la selecci�n
 *  de la estrategia a usar.
 * 	                                                                                                                                               
 ************************************************************************************************************************/
import java.util.ArrayList;

public class Agente implements Cloneable {

	Estado e = new Estado(null, 0, 0); // Estado inicial

	EspacioDeEstados edestados = new EspacioDeEstados();
	//ArrayList<Nodo> camino;
	double tiempo;

	public void General() throws Exception {

		menu(e);
		// Se empieza a medir el tiempo
		long tb0 = System.nanoTime();
		Problema problema = new Problema(edestados, e);
		menu_estrategia(problema);
		
		
		

		// Se termina de medir el tiempo, y se calcula el total
		long tb2 = System.nanoTime();

		System.out.println("\n El tiempo tardado es: " + (tb2 - tb0) + " Nanosegundos");
	}

	// Menu principal
	public void menu(Estado e) throws Exception {
		boolean salir = false;
		
		String cadena_bienvenida = "Bienvenido al sistema, por favor, elija una opcion: \n";
		String cadena_menu = "1: Introducir datos por teclado \n2: Obtener los datos desde un archivo \n3: Salir";

		int Opcion;
		

		System.out.println(cadena_bienvenida);
		do {
			Opcion = Leer.entero(cadena_menu);

			switch (Opcion) {
			case 1:
				salir = true;
				e.datos_teclado();
				break;

			case 2:
				salir = true;
				e.datos_archivo(link());

				break;
			case 3:
				salir = true;
				System.exit(0);
			default:
				System.out.println("Opcion incorrecta, vuelva a marcar una opcion\n");

			}
		} while (!salir);

	}

	// Menu para elegir la estrategia
	public void menu_estrategia(Problema problema) throws Exception {
		boolean salir=false;
		String cadena_bienvenida = "\n Seleccion de estrategia: \n";
		String cadena_menu = "1: Profundidad Simple \n2: Profundidad Acotada \n3: Asterisco \n4: Costo Uniforme\n5: Anchura";
		//Problema problema = new Problema(edestados, e);
		int Opcion;
		int cota_max;
		int profMax=999999;
		Nodo n=null;
		ArrayList<Nodo> camino=null;
		System.out.println(cadena_bienvenida);
		do {
		Opcion = Leer.entero(cadena_menu);

		switch (Opcion) {
		case 1:
			salir=true;
			n = problema.Busqueda("Profundidad Simple", profMax);
			//if (n != null)
			//	camino = problema.resultado(n);
			break;

		case 2:
			salir=true;
			//cota_max = Leer.entero("�Cual es la cota m�xima de busqueda?");
			n = problema.Busqueda("Profundidad Acotada", profMax);

			//n = problema.Busqueda("Profundidad Acotada", cota_max);
			//if (n != null)
			//	camino = problema.resultado(n);

			break;

		case 3:
			salir=true;
			n = problema.Busqueda("Asterisco", profMax);
			//camino = problema.resultado(n);

			break;
		case 4:
			salir=true;
			n = problema.Busqueda("CostoUniforme", profMax);
			//camino = problema.resultado(n);

			break;

		case 5:
			salir=true;
			n = problema.Busqueda("Anchura", profMax);
			//camino = problema.resultado(n);
			break;

		default:
			System.out.println("Opcion incorrecta, vuelva a marcar una opcion\n");

		}
		}while(!salir);
		// Comprueba si existe la solucion
		if (n != null) {
			camino = problema.resultado(n);
			problema.pintaSolucion(camino);
		}else
			System.out.println("\n NO HAY SOLUCI�N O LIMITE DE COTA SUPERADO");
		
	
		
		
	}

	// Metodo para poner la ruta del archivo
	public String link() throws FileNotFoundException {
		boolean salir=false;
		String cadena="Pulse 1 si quiere introducir la ruta del archivo,\n pulse 2 para dejar la de por defecto\n";
		int opcion;
		String link="";
		do {
			opcion= Leer.entero(cadena);
		
		switch(opcion) {
		case 1:
			salir=true;
			link = Leer.cadena("Ponga la ruta...");
			break;
		case 2:
			salir=true;
			link= "c:/Users/rizos/Desktop/Problema.txt";
			break;
			default:
				System.out.println(" opcion incorrecta, vuelva a escribirla\n");
		}
		
		}while(!salir);
		return link;
	}

}
