package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: FronteraCola
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Implementa la frontera, donde se guardaran todos los nodos del arbol en una cola con prioridad
 * 	                                                                                                                                               
 ************************************************************************************************************************/

import java.util.PriorityQueue;

public class FronteraCola {

	private PriorityQueue<Nodo> frontera;

	//Contructores
	private FronteraCola() {
		frontera = new PriorityQueue<Nodo>();
	}

	public static FronteraCola crearFrontera() {
		return new FronteraCola();
	}

	//Operaciones sobre los nodos:
	
	public PriorityQueue<Nodo> getFrontera() {
		return frontera;
	}

	public Nodo eliminar() {
		Nodo n = this.frontera.poll();

		return n;

	}

	public void insertar(Nodo n) {
		this.frontera.add(n);
	}

	public boolean esVacia() {
		if (this.frontera.size() > 0)
			return false;
		else
			return true;
	}
	public void limpiar() {
		frontera.clear();
	}


}
