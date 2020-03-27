package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Fronteralista
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora Gonz·lez 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Implementa la frontera, donde se guardaran todos los nodos del arbol en una lista
 * 	                                                                                                                                               
 ************************************************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class FronteraLista {
	private ArrayList<Nodo> frontera;
	
	private FronteraLista() {
		frontera=new ArrayList<Nodo>();
	} 
	
	/*
	 * metodo q crea una lista de nodos ordenados por valor
	 * para ello esta implementado el compare en la clase nodo
	 * Collections.sort
	 * se usa para establecer un orden ascendente al a√±adir objetos a la lista,
	 * de acuerdo con el orden  implementado en el compareTo de la clase nodo
	 * 
	 */
	
	public static  FronteraLista crearFrontera() {
	FronteraLista f=new	FronteraLista();
	Collections.sort(f.getFronteraLista());
	return f;
	
	//Informacion sobre los nodos de la lista	    
	}
	public ArrayList<Nodo> getFronteraLista(){
		return frontera;
	}
	
	public Nodo eliminar() {
		Nodo n=	frontera.remove(0);
		return n;
	}
	public void insertar (Nodo n) {
		frontera.add(n);
	}
	public boolean esVacia() {
		if(frontera.size()>0)
			return false;
		else 
			return true;
	}

}
