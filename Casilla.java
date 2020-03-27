package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE:  Casilla
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Indica las caracteristica de una parcela del terreno, indicando en que posicion esta y la cantidad de tierra que tiene
 * 	                                                                                                                                               
 ************************************************************************************************************************/
public class Casilla {

	private int fila, columna, cantMax, cantAct;
	private boolean completo;

	//Contructor
	public Casilla(int fila,int columna,int cantMax, int cantAct) {
		
		this.fila=fila;
		this.columna=columna;
		this.cantMax=cantMax;
		this.cantAct=cantAct;
		completo=false;
		
	}



	//Metodos get�s, set�s y ToString

	//Metodos get�s, set�s y ToString

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getCantMax() {
		return cantMax;
	}

	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}

	public int getCantAct() {
		return cantAct;
	}

	public void setCantAct(int cantAct) {
		this.cantAct = cantAct;
	}

	public boolean getCompleto() {
		return completo;
	}

	public void setCompleto(boolean visitado) {
		this.completo = visitado;
	}

	public boolean equals(Object obj) {
		Casilla c = (Casilla) obj;
		
		if (getCantAct() == c.getCantAct()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
	       return "(" + getFila() + "," + getColumna() +','+ getCantAct()+ ')';
	   }   

}
