package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Distribuccion 
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Indica la cantidad de arena que se le lleva a una posicion.
 * 	                                                                                                                                               
 ************************************************************************************************************************/

public class Distribucion {
	private int arena, f, c;

	//constructor
	public Distribucion(int arena, int f, int c) {
		this.arena = arena;
		this.f = f;
		this.c = c;
	}

	//Metodos get´s, set´s y ToString
	public int getArena() {
		return arena;
	}

	public void setArena(int arena) {
		this.arena = arena;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public String toString() {
		return "(" + getArena() + ",(" + getF() + "," + getC() + "))";
	}
}
