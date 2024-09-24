package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class Secundaria {
	
	private String nombre;
	private ArrayList<Anio> anios;
	
	
	public Secundaria(String nombre, Integer cantidadSalitas) {
		this.nombre = nombre;
		this.anios = new ArrayList<>();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Anio[] getAnios() {
		return anios;
	}


	public void setAnios(Anio[] anios) {
		this.anios = anios;
	}
	public void crearAnios(Anio anio) {
		for (Integer i = 0; i < anios.length; i++) {
			if (anios[i] == null) {
				anios[i] = anio;
				break;
			}
		}
	}


	@Override
	public String toString() {
		return "Secundaria [nombre=" + nombre + ", años=" + Arrays.toString(anios) + "]";
	}
	
	
}
