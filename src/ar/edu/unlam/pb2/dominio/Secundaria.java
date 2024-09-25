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

	public ArrayList<Anio> getAnios() {
		return anios;
	}


	public void setAnios(ArrayList<Anio> anios) {
		this.anios = anios;
	}

	public Boolean crearAnios(Anio anio) {
		return anios.add(anio);
	}
	
	
	@Override
	public String toString() {
		return "Secundaria [nombre=" + nombre + ", años=" +(anios) + "]";
	}
	
	
}
