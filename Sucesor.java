package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Sucesor
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * Da la información sobre los sucesores, como la accion aplicada, el estado y el coste
 * 	                                                                                                                                               
 ************************************************************************************************************************/

public class Sucesor {
	private Accion accion;
	private Estado estado;
	private int coste;
	
	//contructor
	public Sucesor(Accion a, Estado e, int c) {
		accion =a;
		estado =e;
		coste =c;
	}

	//Metodos get´s y set´s 
	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
	

}
