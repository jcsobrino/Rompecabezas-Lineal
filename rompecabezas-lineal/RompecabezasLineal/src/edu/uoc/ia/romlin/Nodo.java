package edu.uoc.ia.romlin;

import java.util.Arrays;
import java.util.UUID;

public class Nodo {

	UUID id;
	Integer[] estado;
	UUID idNodoPadre;
	Operador operadorGenerador;

	public Nodo(Integer[] estado, UUID idNodoPadre, Operador operadorGenerador) {

		this.id = UUID.randomUUID();
		this.estado = estado;
		this.idNodoPadre = idNodoPadre;
		this.operadorGenerador = operadorGenerador;
	}

	@Override
	public String toString() {

		return Arrays.toString(estado) + " " + operadorGenerador;
	}
}
