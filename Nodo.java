package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Nodo
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Informacion de los nodos, como su costo, valor, padre...
 * 	                                                                                                                                               
 ************************************************************************************************************************/

public class Nodo implements Comparable<Nodo> {

	private int profundidad;
	private int costo;
	private Estado estado;
	private Nodo padre;
	private Accion accion;
	private int valor;
	private int valorMaximo = 10000; // valor mÃ¡ximo para generar aleatoriamente

	// Constructor del nodo padre
	public Nodo(Estado eInicial) {
		profundidad = 0;
		costo = 0;
		estado = eInicial;
		padre = null;
		accion = null;
		valor = 0;
	}
	//Constructor de nodos sucesores
	public Nodo(Estado est, Nodo padre, Accion a) {
		this.padre = padre;
		profundidad = this.padre.getProfundidad() + 1;
		costo = this.padre.getCosto() + a.getCoste();
		estado = est;
		accion = a;
		valor = (int) (valorMaximo * Math.random() + 1);// valor q se generarÃ¡ aleatoriamente

	}
	public Nodo(Estado est, Nodo padre, Accion a,int costo,int valor) {
		this.padre = padre;
		profundidad = padre.getProfundidad() + 1;
		this.costo = costo;//padre.getCosto() + a.getCoste();
		estado = est;
		accion = a;
		this.valor = valor;

	}

	//metodos get´s y set´s
	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	//Comparador de nodos
	public int compareTo(Nodo n) {
		if (getValor() < n.getValor())
			return -1;
		else if (getValor() > n.getValor())
			return +1;
		return 0;
	}

}
