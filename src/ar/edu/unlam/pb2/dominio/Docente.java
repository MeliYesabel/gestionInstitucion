package ar.edu.unlam.pb2.dominio;

public class Docente {
	
	private String nombre;
	private Integer dni;
	private HistorialAcademico experiencia;
	
	public Docente(String nombre, Integer dni) {
		this.nombre = nombre;
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public HistorialAcademico getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(HistorialAcademico experiencia) {
		this.experiencia = experiencia;
	}
	@Override
	public String toString() {
		return "[" + nombre + "," + dni + "]";
	}



}
