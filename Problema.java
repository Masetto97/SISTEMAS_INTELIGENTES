package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Problema
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 *  Crea el arbol y muestra la solución.
 * 	                                                                                                                                               
 ************************************************************************************************************************/

import java.io.*;
import java.util.*;

public class Problema {
	private Estado einicial;
	private EspacioDeEstados edEstados;

	public Problema() {

	}

	public Problema(EspacioDeEstados ede, Estado eini) {
		edEstados = ede;
		einicial = eini;
		System.out.println("Este es el estado inicial del terreno\n" + "==========================================\n");
		eini.pintar_terreno();
	}

	/*
	 * Metodo funcionObjetivo
	 */
	public boolean funcionObjetivo(Estado e) {
		return e.esObjetivo();
	}

	/*
	 * Getters and Setters
	 */
	public Estado getEinicial() {
		return einicial;
	}

	public void setEinicial(Estado einicial) {
		this.einicial = einicial;
	}

	public EspacioDeEstados getEdEstados() {
		return edEstados;
	}

	public void setEdEstados(EspacioDeEstados edEstados) {
		this.edEstados = edEstados;
	}

	/*
	 * Metodo BUSQUEDA
	 */
	public Nodo Busqueda(String estrategia, int prof_max) throws Exception {

		boolean solucion = false;
		Nodo actual = null;
		FronteraCola fCola = FronteraCola.crearFrontera();
		Nodo padre = new Nodo(this.einicial);
		int cota=30; // cota maxima para prof. iterativa
		
		int prof_acotada=cota;
		int valor = 0;
		Nodo n = null;
		Serializa id = null;
		// esctructura para guardar la lista de estados visitados con la menor
		// valoracion
		Map<String, Integer> estadoCodigos = new HashMap();
		String codigo = "";
		int funcion;

		System.out.println("generando arbol.......................................................");

		ArrayList<Sucesor> LS = new ArrayList<Sucesor>();
		//ArrayList<Nodo> LN = new ArrayList<Nodo>();

		if (estrategia == "Asterisco") {
			padre.setValor(einicial.heuristica());
		}
		codigo = id.encriptarMD5(einicial.estadoCadena()); //tabla hash
		funcion = padre.getValor();
		estadoCodigos.put(id.encriptarMD5(einicial.estadoCadena()), funcion);
		fCola.insertar(padre); //frontera cola

		while (!solucion && !fCola.esVacia()) {

			actual = fCola.eliminar();
			
			if (actual.getEstado().esObjetivo()) {
				
				solucion = true;

			} else if ((actual.getProfundidad()) <= prof_max) {

		
				LS = edEstados.funcionSucesor(actual); //lista de sucesores

				for (int i = 0; i < LS.size(); i++) {

					switch (estrategia) {
					case "Anchura":
						valor = actual.getProfundidad() + 1;
						break;
					case "Asterisco":
						valor = LS.get(i).getCoste() + LS.get(i).getEstado().heuristica();
						
						break;
					case "Profundidad Simple":
						//valor =actual.getProfundidad()+1;
						
						valor = (prof_max- (actual.getProfundidad() + 1))*(-1);
						break;
					case "Profundidad Acotada":
						//prof_acotada=cota;
						if(prof_acotada<=prof_max) {
							valor=(prof_acotada-((actual.getProfundidad()) + 1))*(-1);
						}
						else {
							fCola.limpiar();
							fCola.insertar(padre);
							cota+=cota;
							//prof_acotada=prof_acotada+cota;
						
							//valor=(prof_acotada-((actual.getProfundidad()) + 1))*(-1);
						}
						//valor = prof_max - (actual.getProfundidad() + 1);
						//valor=profundidadIterativa(prof_max,cota,actual);

						break;

					case "CostoUniforme":
						valor = LS.get(i).getCoste();
						break;
					default:
						System.out.println("Error en la eleccion de estrategia");
						System.exit(0);
					}
					
					
					
					n = new Nodo(LS.get(i).getEstado(), actual, LS.get(i).getAccion(), LS.get(i).getCoste(), valor);

					// aqui tengo q comprobar si hay alguno dentro con menos valoraciÃ³n
					// si en la lista hay un estado igual comparar la valoraciÃ³n
					//Poda
					codigo = id.encriptarMD5(n.getEstado().estadoCadena());//encriptas el estado del nodo
					
					
					
					if (estadoCodigos.containsKey(codigo)) {// si el estado esta en la lista

						if (estadoCodigos.get(codigo) > n.getValor()) {// si el valor del estado en la lista es mayor

							estadoCodigos.replace(codigo, n.getValor()); // actualizo la lista
							fCola.insertar(n);
						}
					} else {// si no esta en la lista, lo añado a la frontera

						estadoCodigos.put(codigo, n.getValor());
						fCola.insertar(n);
					}
					
				
				}



			}

		}
		
		System.out.println("arbol generado.......................................................");
		if (solucion) {

			System.out.println("Solucion encontrada  ");
			
			return actual;
		} else {
			System.out.println("No se encuentra solucion");
			return null;
		}

	}

	/*
	 * Metodo profundidad acotada iterativa
	 */
	
	/*
	 * Metodo resultado devuelve una lista de nodos con el camino de la solucion
	 */
	public ArrayList<Nodo> resultado(Nodo nodoObjetivo) {
		ArrayList<Nodo> camino = new ArrayList<Nodo>();
		camino.add(nodoObjetivo);
		while (nodoObjetivo.getPadre() != null) {
			camino.add(nodoObjetivo.getPadre());
			nodoObjetivo = nodoObjetivo.getPadre();
		}
		return camino;
	}

	
	

	/*
	 * Metodo pintaSolucion devuelve una lista de nodos con el camino de la solucion
	 */
	public void pintaSolucion(ArrayList<Nodo> camino) throws IOException {

		FileWriter fichero = null;

		// ruta de salida del fichero

		fichero = new FileWriter(link());
		String S;

		fichero.write("Profundidad final: " + camino.size() + ", costo final: " + camino.get(0).getCosto());

		System.out.println("Profundidad: " + camino.size() + ", costo: " + camino.get(0).getCosto());
		System.out.println("=======================================================================\n\n");
		
		fichero.write("\n\nLISTA DE ACCIONES: \n\n");
		
		
		
		for (int i = camino.size() - 1; i >= 0; i--) {

			if (camino.get(i).getPadre() != null) {
				S=camino.get(i).getAccion().toString();
				System.out.println(S);
				fichero.write(S+"\n");

			} else
				System.out.println("None");
			if(camino.get(i).getValor() <0)
				camino.get(i).setValor((camino.get(i).getValor())*(-1));
			System.out.println("Valor: "+
					camino.get(i).getValor() + " Profundidad:  " + camino.get(i).getProfundidad() + " Costo: " + camino.get(i).getCosto());

			
			
			System.out.print(camino.get(i).getEstado().getTracX() + " " + camino.get(i).getEstado().getTracY());
			System.out.print(" " + einicial.getK() + " " + einicial.getMax());
			System.out.print(" " + einicial.getTerreno().length + " " + einicial.getTerreno().length + "\n");

			camino.get(i).getEstado().pintar_terreno();

		}
		fichero.close();
	}
	
	//Metodo para poner la ruta de salida archivo
	public String link() {

		boolean salir=false;
		String cadena="Genera fichero de salida: \n Pulse 1 para introducir la ruta del archivo de destino,\n pulse 2 para dejar la ruta por defecto\n";
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
			link= "c:/Users/rizos/Desktop/SOLUCION.txt";
			break;
			default:
				System.out.println(" opcion incorrecta, vuelva a escribirla\n");
		}
		
		}while(!salir);
		return link;

	}

}

