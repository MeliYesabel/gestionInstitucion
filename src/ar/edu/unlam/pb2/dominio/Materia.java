package ar.edu.unlam.pb2.dominio;

public class Materia {
	private String nombre;
	private Docente profesor;

	public Materia(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Docente getProfesor() {
		return profesor;
	}

	public void setProfesor(Docente profesor) {
		this.profesor = profesor;
	}

}
