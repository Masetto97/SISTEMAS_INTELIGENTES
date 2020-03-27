package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE:  Accion
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Crear las caracteristicas necesarias que conlleva hacer una accion: Coste, Tierra repartida, Posicion del tractor...
 * 	                                                                                                                                               
 ************************************************************************************************************************/
import java.util.ArrayList;

public class Accion {

	
	private int tf; //Fila donde esta el tractor
	private int tc; //Columna donde esta el tractor
	private int coste;
	private ArrayList<Distribucion> lisDis = new ArrayList<Distribucion>(); //Lista de todas las distribuciones posibles a realizar


	//Constructor de la clase
	
	public Accion(int tf, int tc, ArrayList<Distribucion> ld, int coste) {
		this.tf=tf;
		this.tc=tc;		
		this.coste=coste;
		for(int i=0;i<ld.size();i++) {
			lisDis.add(i,ld.get(i));
		}

	}

	//Metodos get�s, set�s y ToString
	
	public int getTf() {
		return tf;
	}

	public void setTf(int tf) {
		this.tf = tf;
	}

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public ArrayList<Distribucion> getLisDis() {
		return lisDis;
	}
	
	public Distribucion getDis( int i) {
		return lisDis.get(i);
	}

	public void setLisDis(ArrayList<Distribucion> lisDis) {
		this.lisDis = lisDis;
	}
	public String toString() {
		String dis="";
		for(int i=0; i< lisDis.size();i++) {
		dis=dis + lisDis.get(i).toString();
		}
	
		return "((" + getTf() + "," + getTc() + ") , [" + dis +"]," + getCoste() + "),\n";
	}

}
