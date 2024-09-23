package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;

public class Institucion {
	private String nombre;
	private Jardin jardin;
	private Primaria primaria;
	private Secundaria secundaria;
	private ArrayList<Alumno> alumnos;//todavia no se si le voy a dar uso
	private ArrayList<Docente> docentes;//lo mismo
	
	public Institucion(String nombre) {
		this.nombre = nombre;
		this.alumnos = new ArrayList<>();
		this.docentes = new ArrayList<>();
		this.jardin = new Jardin("Jardin "+ nombre, ColorDeSala.values().length);
		this.primaria = new Primaria("Primaria " + nombre, 6);
		this.secundaria = new Secundaria("Secundaria " + nombre, 6);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Jardin getJardin() {
		return jardin;
	}

	public void setJardin(Jardin jardin) {
		this.jardin = jardin;
	}

	public Primaria getPrimaria() {
		return primaria;
	}

	public void setPrimaria(Primaria primaria) {
		this.primaria = primaria;
	}

	public Secundaria getSecundaria() {
		return secundaria;
	}

	public void setSecundaria(Secundaria secundaria) {
		this.secundaria = secundaria;
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	
	public ArrayList<Docente> getDocente() {
		return docentes;
	}
	
	public Docente buscarDocente(Integer dni) {
		for (Docente a : docentes) {
			if (a.getDni().equals(dni)) {
				return a;
			}
		}	
		return null;
	}
	
	public Boolean incoportarDocente(Docente docente) {

		if (buscarDocente(docente.getDni()) == null) {
			docentes.add(docente);
			return true;
		}
		return false;
	}
	
	public Boolean bajaDocente(Integer dni) {

		if (buscarDocente(dni) != null) {
			docentes.remove(buscarDocente(dni));
			return true;
		}
		return false;
	}
	
	public Alumno buscarAlumno(Integer dni) {
		for (Alumno a : alumnos) {
			if (a.getDni().equals(dni)) {
				return a;
			}
		}	
		return null;
	}
	
	public Boolean incoportarAlumno(Alumno alumno) {

		if (buscarDocente(alumno.getDni()) == null) {
			alumnos.add(alumno);
			return true;
		}
		return false;
	}
	
	public Boolean bajaAlumno(Integer dni) {

		if (buscarAlumno(dni) != null) {
			alumnos.remove(buscarAlumno(dni));
			return true;
		}
		return false;
	}
	
	public void setDocente(ArrayList<Docente> docente) {
		this.docentes = docente;
	}
	

}
