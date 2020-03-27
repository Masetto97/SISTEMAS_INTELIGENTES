package sistemasInteligentes;

/*************************************************************************************************************************
 * NOMBRE CLASE:  EspacioDeEstados
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

public class EspacioDeEstados {

	private ArrayList<Nodo> listaNodos;
	public Estado estado;

	//Metodos get�s, set�s y ToString
	public ArrayList<Nodo> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(ArrayList<Nodo> listaNodos) {
		this.listaNodos = listaNodos;
	}

	public EspacioDeEstados() {

	}

	
	public ArrayList<Sucesor> funcionSucesor(Nodo e) {
		ArrayList<Sucesor> sucesores = new ArrayList<Sucesor>();
		ArrayList<Casilla> validos = e.getEstado().movimientosValidos();
		ArrayList<Accion> la = new ArrayList<Accion>();
		ArrayList<Distribucion> ld = new ArrayList<Distribucion>();

		e.getEstado().accionesPosibles(0, validos, ld, la);
		e.getEstado().todasPosibles(la, validos);

		Estado nuevo = new Estado();

		for (int i = 0; i < la.size(); i++) {
			nuevo = e.getEstado().clonar();
			nuevo.aplicar_accion(la.get(i));

			sucesores.add(new Sucesor(la.get(i), nuevo, (la.get(i).getCoste() + e.getCosto())));

		}

		return sucesores;
	}

	//Metodo que muestra todos los sucesores ^*^
	public void mostrarSucesores(ArrayList<Sucesor> lsuc) {
		for (int i = 0; i < lsuc.size(); i++) {
			System.out.println("Sucesor n�: " + i + 1);
			lsuc.get(i).getEstado().pintar_terreno();
		}
	}

}
