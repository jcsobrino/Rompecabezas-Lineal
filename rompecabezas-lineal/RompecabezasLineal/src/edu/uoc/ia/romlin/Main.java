package edu.uoc.ia.romlin;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		Integer[] estadoInicial = { 4, 2, 1, 3 };
		Integer[] estadoFinal = { 1, 4, 2, 3 };

		List<Nodo> solucion = BuscadorSoluciones.buscar(estadoInicial, estadoFinal, MetodoBusqueda.busquedaAmplitud);

		System.out.println(solucion);

		solucion = BuscadorSoluciones.buscar(estadoInicial, estadoFinal, MetodoBusqueda.busquedaProfundidad);

		System.out.println(solucion);
	}

}
