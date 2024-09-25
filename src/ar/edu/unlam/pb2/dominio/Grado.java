package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Grado {

	private LinkedList<Alumno> alumnos;
	private Docente docente;
	private Numero grado;
	private final Integer EDAD_MINIMA;
	private Integer capacidad;

	public Grado(Numero grado) {
		this.alumnos = new LinkedList<>();
		this.grado = grado;
		EDAD_MINIMA = grado.ordinal() + 6;
	}

	public LinkedList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(LinkedList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Numero getGrado() {
		return grado;
	}

	public void setGrado(Numero grado) {
		this.grado = grado;
	}

	public Integer getEDAD_MINIMA() {
		return EDAD_MINIMA;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Boolean agregarAlumno(Alumno alumno) {
		if (alumnos.size() < capacidad) {
			alumnos.add(alumno);
			return true;
		}
		return false;
	}

	public Boolean asignarDocente(Docente docente) {
		if (tieneExperiencia(docente)) {
			setDocente(docente);
			return true;
		}
		return false;
	}

	public Boolean tieneExperiencia(Docente docente) {
		if (docente.getExperiencia().getGrado()[this.grado.ordinal()]) {
			return true;
		}
		return false;
	}

	public Boolean bajaAlumno(Integer dni) {
		Alumno deBaja = buscarAlumnoDNI(dni);
		if (deBaja != null) {
			alumnos.remove(deBaja);
			return true;
			}
		return false;
	}

	public Boolean bajaDocente() {

		if (this.docente != null) {
			this.docente = null;
			return true;
		}
		return false;
	}

	public Alumno buscarAlumnoDNI(Integer dni) {
		for(Alumno alumno : alumnos) {
			if(alumno.getDni().equals(dni)) {
				return alumno;
			}
		}
		return null;
	}

	public Boolean tomarAsistencia(Fecha fecha, Alumno alumno, Boolean asistencia) {
		Asistencia nueva = new Asistencia(fecha, asistencia);
		if (!existeFecha(fecha)) {
			for (int i = 0; i < alumno.getAsistencias().size(); i++) {
				if (alumno.getAsistencias().get(i) != null) {
					alumno.getAsistencias().set(i, nueva);
					return true;
				}
			}
		}
		return false;
	}

	private Boolean existeFecha(Fecha fecha) {

		if (alumnos.get(0) != null && alumnos.get(0).getAsistencias().get(0) != null
				&& alumnos.get(0).getAsistencias().get(0).getFecha().getMes() == fecha.getMes()
				&& alumnos.get(0).getAsistencias().get(0).getFecha().getDia() == fecha.getDia()) {
			return true;

		}
		return false;
	}

	public void cargarNota(Integer nota, Alumno alumno) {
		Evaluacion evaluacion = new Evaluacion(nota);
		
		for (Integer i = 0; i < alumno.getEvaluaciones().size(); i++) {
			if (alumno.getEvaluaciones().get(i) != null) {
				alumno.getEvaluaciones().set(0, evaluacion);
				break;
			}
		}	
		
	}

	@Override
	public String toString() {
		return grado + "\n" + docente + "\nalumnos=" + alumnos;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

}
