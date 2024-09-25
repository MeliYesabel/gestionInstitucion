package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;

public class Alumno extends Persona{
	private String nombre;
	private Integer dni;
	private Integer edad;
	private HistorialAcademico historial;
	private ArrayList<Asistencia> asistencias;
	private ArrayList<Evaluacion> evaluaciones;
	
	public Alumno(String nombre, Integer dni, Integer edad) {
		super(nombre, dni);
		this.edad = edad;
		this.asistencias = new ArrayList<>(); // segun el Ministerio de Relaciones Exteriores y Culto un ciclo escolar tiene 190 dias
		this.evaluaciones = new ArrayList<>();
	}
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public HistorialAcademico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialAcademico historial) {
		this.historial = historial;
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
	public ArrayList<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(ArrayList<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}
	public ArrayList<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", dni=" + dni + ", edad=" + edad + ", historial=" + historial
				+ ", asistencias=" + asistencias + ", evaluaciones=" + evaluaciones + "]";
	}

	


}
