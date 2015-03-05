package edu.uoc.ia.romlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuscadorSoluciones {

	private static List<Nodo> nodosAExpandir = new ArrayList<Nodo>();
	private static List<Nodo> nodosExpandidos = new ArrayList<Nodo>();

	public static List<Nodo> buscar(Integer[] estadoIni, Integer[] estadoFin, MetodoBusqueda metodo) {

		if (!estadoBienFormado(estadoIni) || !estadoBienFormado(estadoFin)) {

			throw new IllegalArgumentException("Estados de entrada incorrectos");
		}

		nodosAExpandir.clear();
		nodosExpandidos.clear();

		Nodo nodoInicial = new Nodo(estadoIni, null, null);
		nodosAExpandir.add(nodoInicial);

		Nodo nodoActual = null;
		boolean solucionEncontrada = false;

		while (!solucionEncontrada && !nodosAExpandir.isEmpty()) {

			nodoActual = nodosAExpandir.remove(0);

			if (!existeNodoExpandido(nodoActual)) {

				nodosExpandidos.add(nodoActual);
				solucionEncontrada = Arrays.equals(nodoActual.estado, estadoFin);

				if (!solucionEncontrada) {

					List<Nodo> nuevosNodos = Arrays.asList(aplicarOperador(nodoActual, Operador.II),
							aplicarOperador(nodoActual, Operador.IC), aplicarOperador(nodoActual, Operador.ID));

					if (metodo == MetodoBusqueda.busquedaAmplitud) {

						nodosAExpandir.addAll(nuevosNodos);

					} else {

						nodosAExpandir.addAll(0, nuevosNodos);
					}
				}
			}
		}

		return solucionEncontrada ? construirSolucion(nodoActual) : null;
	}

	static private Nodo buscarNodoPadre(Nodo nodo) {

		for (int i = 0; i < nodosExpandidos.size(); i++) {

			Nodo aux = nodosExpandidos.get(i);

			if (aux.id.equals(nodo.idNodoPadre)) {

				return aux;
			}
		}
		return null;
	}

	static private Nodo aplicarOperador(Nodo nodoPadre, Operador op) {

		Integer[] estadoPadre = nodoPadre.estado;
		Integer[] estadoHijo = Arrays.copyOf(estadoPadre, estadoPadre.length);

		switch (op) {

		case II:
			estadoHijo[0] = estadoPadre[1];
			estadoHijo[1] = estadoPadre[0];
			break;
		case IC:
			estadoHijo[1] = estadoPadre[2];
			estadoHijo[2] = estadoPadre[1];
			break;
		case ID:
			estadoHijo[2] = estadoPadre[3];
			estadoHijo[3] = estadoPadre[2];
			break;
		}

		return new Nodo(estadoHijo, nodoPadre.id, op);
	}

	static private List<Nodo> construirSolucion(Nodo nodoFinal) {

		List<Nodo> solucion = new ArrayList<Nodo>();
		Nodo aux = nodoFinal;

		while (aux != null) {

			solucion.add(0, aux);
			aux = buscarNodoPadre(aux);
		}

		return solucion;
	}

	static private boolean estadoBienFormado(Integer[] estado) {

		Integer[] aux = Arrays.copyOf(estado, estado.length);
		Arrays.sort(aux);
		return Arrays.equals(aux, new Integer[] { 1, 2, 3, 4 });
	}

	static private boolean existeNodoExpandido(Nodo nodo) {

		for (int i = 0; i < nodosExpandidos.size(); i++) {

			if (Arrays.equals(nodosExpandidos.get(i).estado, nodo.estado)) {

				return true;
			}
		}

		return false;
	}
}
