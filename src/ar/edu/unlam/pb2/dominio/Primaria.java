package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class Primaria {
	
	private String nombre;
	private Grado grados [];
	
	
	public Primaria(String nombre, Integer cantidadGrados) {
		this.nombre = nombre;
		this.grados = new Grado[cantidadGrados];
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Grado[] getGrados() {
		return grados;
	}


	public void setGrados(Grado[] grados) {
		this.grados = grados;
	}

	public void crearGrados(Grado grado) {
		for (Integer i = 0; i < grados.length; i++) {
			if (grados[i] == null) {
				grados[i] = grado;
				break;
			}
		}
	}

	@Override
	public String toString() {
		return  nombre + ", grados=" + Arrays.toString(grados) + "]";
	}
	
	
}
