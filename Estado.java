package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE:  Estado
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora González 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Tiene las funcionalidades de un estado, como consultar la informacion de este, copiarlo y clonarlo
 * 	                                                                                                                                               
 ************************************************************************************************************************/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Estado {

	
	private Casilla[][] terreno;
	private int TracX, TracY; // coordenadas del tractor dentro del terreno
	private int V, K, max; // V toda la tierra del terreno, K cantidad deseada en cada casilla



	//Constructores 
	public Estado() {
		
	}
	public Estado(Casilla[][] terreno, int x, int y) {
		this.terreno = terreno;
		TracX = x;
		TracY = y;
	}

	//metodo que clona un estado actual
	public Estado clonar() {
		
		
		Estado clon=new Estado (copiar_terreno(), this.getTracX(), this.getTracY());
		clon.setK(this.getK());
		clon.setMax(this.getMax());
		clon.setV(this.getV());
		
		return clon;
		
	}
	//Metodo que copia un terreno 
	public Casilla[][] copiar_terreno() {
		Casilla [][] copia= new Casilla [terreno.length][terreno.length];
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				copia[i][j]=new Casilla(terreno[i][j].getFila(),terreno[i][j].getColumna(),terreno[i][j].getCantMax(),terreno[i][j].getCantAct());
				
			}
			
		}
		return copia;
	}


	//Metodos get�s, set�s y ToString

	
	public int getTracX() {
		return TracX;
	}

	public void setTracX(int x) {
		TracX = x;
	}

	public int getTracY() {
		return TracY;
	}

	public void setTracY(int y) {
		TracY = y;
	}

	public int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}

	public int getK() {
		return K;
	}

	public void setK(int k) {
		K = k;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Casilla[][] getTerreno() {
		return terreno;
	}

	public void setTerreno(Casilla[][] terreno) {
		this.terreno = terreno;
	}



	public void pintar_terreno() {

		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {

				System.out.print(terreno[i][j].getCantAct() + " ");
			}
			System.out.println();
		}
	}


	public void datos_archivo(String archivo) throws FileNotFoundException {

		
		FileReader Fichero = new FileReader(archivo);
		Scanner b = new Scanner(Fichero);
		String cadena;
		int cont = 0;
		int q = 0;
		int filas = 0, columnas = 0;
		boolean fin = false;

		while (b.hasNextLine() || fin == true) {

			if (cont == 0) {

				cadena = b.nextLine();
				String a[] = cadena.split(" ");

				TracX = Integer.parseInt(a[0]);
				TracY = Integer.parseInt(a[1]);
				K = Integer.parseInt(a[2]);
				max = Integer.parseInt(a[3]);
				filas = Integer.parseInt(a[4]);
				columnas = Integer.parseInt(a[5]);
				terreno = new Casilla[filas][columnas];
				cont++;

			} else if (cont > 0) {

				if (!b.hasNext()) {
					fin = true;
				} else {

					for (int i = 0; i < filas; i++) {
						for (int j = 0; j < columnas; j++) {

							cadena = b.next();
							q = Integer.parseInt(cadena);
							getTerreno()[i][j] = new Casilla(i, j, max, q);
						}
					}
				}

			}
		}
		//pintar_terreno();
		b.close();
	}

	public void datos_teclado() throws Exception {
		int filas = 0, columnas = 0;
		boolean dentro = false;

		K = Leer.entero("¿Cual es la cantidad de tierra que debe tener cada parcela al final?");
		max = Leer.entero("¿Cual es la cantidad maxima de tierra que puede tener cada parcela?");
		columnas = Leer.entero("¿Cuantas columnas tiene el terreno?");
		filas = Leer.entero("¿Cuantas filas tiene el terreno?");
		V = K * filas * columnas;

		
		terreno = new Casilla[filas][columnas];
		generar_terreno();

		do {

			
			TracX = Leer.entero("¿En que coordenada X est� el tractor?");
			
			TracY = Leer.entero("¿En que coordenada Y est� el tractor?");

			if (estaDentro(TracX, TracY)) {

				dentro = true;

			} else {

				System.out.println("Seleccione una coordenada dentro del terreno");
			}

		} while (!dentro);
	}


	public void generar_terreno() {

		boolean repartido = false;
		int tierra = V;
		int puesto;
		int total;

		System.out.println("EL VALOR DE V ES. " + V);

		for (int a = 0; a < terreno.length; a++) {
			for (int b = 0; b < terreno.length; b++) {

				terreno[a][b] = new Casilla(a, b, max, 0);

			}
		}

		do {

			for (int i = 0; i < terreno.length; i++) {

				for (int j = 0; j < terreno.length; j++) {

					if (tierra <= 0) {

						repartido = true;

					} else if (tierra >= max) {

						puesto = (int) (Math.random() * max);
						
						total = puesto + terreno[i][j].getCantAct();

						if (total <= max) {
							terreno[i][j].setCantAct(total);
						
							tierra = tierra - puesto;
						}

					} else if (tierra <= max && tierra > 1) {

						puesto = (int) (Math.random() * tierra);
						total = puesto + terreno[i][j].getCantAct();

						if (total <= max) {

							terreno[i][j].setCantAct(total);
							tierra = tierra - puesto;

						}

					} else if (tierra == 1) {

						total = 1 + terreno[i][j].getCantAct();
						if (total <= max) {

							terreno[i][j].setCantAct(total);
							tierra = tierra - 1;
							repartido = true;
						}
					}

				}
			}
		} while (repartido != true);
	//	pintar_terreno();
		
	}


	public boolean estaDentro(int x, int y) {
		return (getTracX() >= 0) && (getTracX() <= getTerreno().length - 1) && (getTracY() >= 0)
				&& (getTracY() <= getTerreno().length - 1);

	}

	
	public ArrayList<Casilla> movimientosValidos() {

		// lista de casillas adyacentes en linea recta al tractor
		ArrayList<Casilla> list = new ArrayList<Casilla>();

		if (getTracX() != 0)
			list.add(getTerreno()[getTracX() - 1][getTracY()]);// NORTE
		if (getTracX() < getTerreno().length - 1)
			list.add(getTerreno()[getTracX() + 1][getTracY()]);// SUR
		if (getTracY() != 0)
			list.add(getTerreno()[getTracX()][getTracY() - 1]);// OESTE
		if (getTracY() < getTerreno().length - 1)
			list.add(getTerreno()[getTracX()][getTracY() + 1]);// ESTE

		return list;
	}
	
	/*
	 * Costo Metodo que calcula y asigna el costo a una acción
	 */
	public void Costo(Accion a) {
		int c=1;//se inicializa a 1 para no sumarlo al final
		for(int i=0; i<a.getLisDis().size();i++) {
			c+=a.getDis(i).getArena();
		}
		a.setCoste(c);
		
	}

	/*
	 * accionesPosibles Metodo que genera la lista de las acciones posibles (backtracking)
	 */

	public void accionesPosibles(int etapa, ArrayList<Casilla> validos, ArrayList<Distribucion> ld,
			ArrayList<Accion> la) {

		if ((etapa == validos.size())) {
			Accion a = new Accion(validos.get(etapa - 1).getFila(), validos.get(etapa - 1).getColumna(), ld, 1);
			Costo(a);//recalculamos el costo de la acción
			if (compruebaAccion(sobrante(), ld))
				la.add(la.size(), a);

		} else {
			for (int i = 0; i <= sobrante(); i++) {
				if (cabe(i, validos.get(etapa))) {
					Distribucion e = new Distribucion(i, validos.get(etapa).getFila(), validos.get(etapa).getColumna());
					ld.add(etapa, e);
					accionesPosibles(etapa + 1, validos, ld, la);
					ld.remove(etapa);
				}
			}
		}
	}

	/*
	 * sobrante: Método que calcula la tierra que sobra en una casilla del atributo
	 * terreno
	 */
	public int sobrante() {
		
		int s = getTerreno()[getTracX()][getTracY()].getCantAct() - K;
		if (s < 0)
			return 0;
		else
			return s;
	}

	/*
	 * cabe: Metodo que comprueba si la cantidad de arena a depositar en una casilla
	 * supera el maximo permitido
	 */

	public boolean cabe(int cant, Casilla c) {
		if ((cant + c.getCantAct()) > max)
			return false;
		else
			return true;
	}

	/*
	 * Método que comprueba que una acción es válida, es decir, que el tractor ha
	 * repartido entre sus posibles vecinas toda la arena sobrante que tenía
	 */
	public boolean compruebaAccion(int s, ArrayList<Distribucion> lista) {
		int suma = 0;

		for (int i = 0; i < lista.size(); i++) {
			suma = suma + lista.get(i).getArena();
		}

		if (suma == s)
			return true;
		else
			return false;
	}

	/*
	 * todas Posibles: Metodo que genera todas las acciones posibles de un estado
	 * recibe la lista de acciones posibles generada en el método accionesPosibles, y genera todas las distribuciones posibles
	 * combinando con todas las posibilidades de nuevas coordenadas del tractor
	 */

	public void todasPosibles(ArrayList<Accion> la, ArrayList<Casilla> validos) {
		int f, c, l;
		l = la.size();
		for (int i = 0; i < validos.size() - 1; i++) {
			f = validos.get(i).getFila();
			c = validos.get(i).getColumna();
			for (int j = 0; j < l; j++) {
				Accion a = new Accion(la.get(j).getTf(), la.get(j).getTc(), la.get(j).getLisDis(),
						la.get(j).getCoste());
				a.setTf(f);
				a.setTc(c);
				la.add(a);

			}
		}
	}

	/*
	 * muestraAcciones Muestra por pantalla la lista de acciones posibles de un
	 * estado
	 */

	public void muestraAcciones(ArrayList<Accion> la) {

		System.out.println("La lista de acciones válidas es: \n");
		for (int j = 0; j < la.size(); j++) {
			System.out.println(la.get(j).toString());
		}
	}

	/*
	 * aplicar_accion Método que recibe una accion para generar un nuevo estado
	 */
	public void aplicar_accion(Accion a) {
		int f, c, arena, resto = 0;
		
		for (int i = 0; i < a.getLisDis().size(); i++) {

			f = a.getDis(i).getF(); //fila
			c = a.getDis(i).getC(); //columna
			arena = a.getDis(i).getArena();
			
			resto = resto + arena;
			getTerreno()[f][c].setCantAct(getTerreno()[f][c].getCantAct() + arena);

		}
		
		// resta en el nuevo terreno de la casilla desde donde ha añadido en la accion
		getTerreno()[getTracX()][getTracY()].setCantAct(getTerreno()[getTracX()][getTracY()].getCantAct() - resto);
		setTracX(a.getTf());
		setTracY(a.getTc());
		

	}
	/*
	 * esObjetivo Método que devuelve un boleano a true si el terreno 
	 * tiene un reparto uniforme, de lo contrario devuelve false
	 */
	public boolean esObjetivo() {

		for (int i = 0; i < terreno.length ; i++) {

			for (int j = 0; j < terreno.length; j++) {

				if (terreno[i][j].getCantAct()!=K)
					return false;

			}
		}

		return true;
	}
	
	/*
	 * metodo que calcula la heurística de un estado
	 * equivale al numero de casillas q no tienen la cantidad deseada
	 */
	public int heuristica() {
		int h=0;
		for (int i = 0; i < terreno.length ; i++) {

			for (int j = 0; j < terreno.length; j++) {

				if (terreno[i][j].getCantAct()!=K)
					h=h+1;;

			}
		}
		return h;
	}
	/*
	 * SonIguales Método que compara si dos estados son iguales ^*^
	 */
	public boolean sonIguales(Estado e) {
		boolean iguales = true;
		if ((e.getTracX() != getTracX()) || (e.getTracY() != getTracY()))
			iguales = false;
		for (int i = 0; i < getTerreno().length || !iguales; i++) {
			for (int j = 0; j < getTerreno().length || !iguales; j++) {
				iguales = getTerreno()[i][j].equals(e.getTerreno()[i][j]);
			}
		}
		return iguales;
	}
	
	/*
	 * estadoCadena Método que devuelve un string con la arena de todas las casillas
	 * y la posicion del tractor. Para poder serializar esta cadena y crear así un 
	 * identificador de estado único.
	 */
	
	public String estadoCadena() {
		String cadena="";
		for (int i = 0; i < terreno.length ; i++) {

			for (int j = 0; j < terreno.length; j++) {

				cadena=cadena+ (char)terreno[i][j].getCantAct();
			}
		}
		cadena=cadena+(char) this.getTracX();
		cadena=cadena+(char) this.getTracY();
		return cadena;
	}
	
	
	
} // Fin clase Estado
