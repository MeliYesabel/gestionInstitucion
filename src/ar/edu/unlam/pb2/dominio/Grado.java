package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class Grado {

	private Alumno alumnos[];
	private Docente docente;
	private Numero grado;
	private final Integer EDAD_MINIMA;

	public Grado(Numero grado) {
		this.alumnos = new Alumno[10];
		this.grado = grado;
		EDAD_MINIMA = grado.ordinal() + 6;
	}

	public Alumno[] getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Alumno[] alumnos) {
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
		if (alumno.getEdad() >= EDAD_MINIMA) {
			for (Integer i = 0; i < alumnos.length; i++) {
				if (alumnos[i] == null) {
					alumnos[i] = alumno;
					return true;
				}
			}
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
			for (Integer i = 0; i < alumnos.length; i++) {
				if (alumnos[i].equals(deBaja)) {
					alumnos[i] = null;
					return true;
				}
			}
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

		for (Integer i = 0; i < alumnos.length; i++) {
			if (alumnos[i] != null && alumnos[i].getDni() == dni) {
				return alumnos[i];
			}
		}
		return null;
	}

	public Boolean tomarAsistencia(Fecha fecha, Alumno alumno, boolean asistencia) {
		Asistencia nueva = new Asistencia(fecha, asistencia);
		if (!existeFecha(fecha)) {
			for (Integer i = 0; i < alumno.getAsistencias().size(); i++) {
				if (alumno.getAsistencias().get(i) != null) {
					alumno.getAsistencias().set(i, nueva);
					return true;
				}
			}
		}
		return false;
	}

	private Boolean existeFecha(Fecha fecha) {

		if (alumnos[0] != null && alumnos[0].getAsistencias().get(0) != null
				&& alumnos[1].getAsistencias().get(0).getFecha().getMes() == fecha.getMes()
				&& alumnos[1].getAsistencias().get(0).getFecha().getDia() == fecha.getDia()) {
			return true;

		}
		return false;
	}

	public void cargarNota(Integer nota, Alumno alumno) {
		Evaluacion evaluacion = new Evaluacion(nota);
		
		for (Integer i = 0; i < alumno.getEvaluaciones().size(); i++) {
			if (alumno.getEvaluaciones().get(i) != null) {
				alumno.getEvaluaciones().set(i, evaluacion);
				break;
			}
		}		
	}

	@Override
	public String toString() {
		return grado + "\n" + docente + "\nalumnos=" + Arrays.toString(alumnos);
	}

}
