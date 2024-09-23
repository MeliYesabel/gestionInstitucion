package ar.edu.unlam.pb2.dominio;

import java.util.LinkedList;

public class Sala {
	private final Integer CANTIDAD_DE_ALUMNOS = 10; 
	private final Integer CANTIDAD_DE_MAESTROS = 1;
	private LinkedList<Alumno> alumnos;
	private LinkedList<Docente> maestros;
	private ColorDeSala color;

	public Sala(ColorDeSala color) {
		this.alumnos = new LinkedList<>();
		this.maestros = new LinkedList<>();
		this.color = color;
	}

	public LinkedList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(LinkedList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public LinkedList<Docente> getMaestro() {
		return maestros;
	}

	public void setMaestro(LinkedList<Docente> maestro) {
		this.maestros = maestro;
	}

	public ColorDeSala getColor() {
		return color;
	}

	public void setColor(ColorDeSala color) {
		this.color = color;
	}

//	public Boolean tomarAsistencia(Fecha fecha, Alumno alumno, Boolean asistencia) {
//		Asistencia nueva = new Asistencia(fecha, asistencia);
//		if (!existeFecha(fecha)) {
//			for (Integer i = 0; i < alumno.getAsistencias().length; i++) {
//				if (alumno.getAsistencias()[i] != null) {
//					alumno.getAsistencias()[i] = nueva;
//					return true;
//				}
//			}
//		}	
//		return false;
//	}

//	public Boolean existeFecha(Fecha fecha) {
//		for (Integer i = 0; i < alumnos[1].getAsistencias().length; i++) {
//			if (alumnos[i] != null && alumnos[1].getAsistencias()[i] != null 
//				&& alumnos[1].getAsistencias()[i].getFecha().getMes() == fecha.getMes() 
//				&& alumnos[1].getAsistencias()[i].getFecha().getDia() == fecha.getDia()) {
//				return true;
//			}
//		}
//		return false;
//	}
//	

	public Boolean agregarAlumno(Alumno alumno) {
		
		if (alumnos.size() <= getCantidadAlumno() && buscarAlumnoDNI(alumno.getDni()) == null) {
			alumnos.add(alumno);
			return true;
		}
		return false;
	}

	public Boolean bajaAlumno(Integer dni) {
		if (buscarAlumnoDNI(dni) != null) {
			alumnos.remove(buscarAlumnoDNI(dni));
			return true;
		}
		return false;
	}

	public Docente buscarMaestroPorDNI(Integer dni) {
		for (Docente a : maestros) {
			if (a.getDni().equals(dni)) {
				return a;
			}
		}
		return null;
	}

	public Boolean asignarMaestro(Docente docente) {
		if (buscarMaestroPorDNI(docente.getDni()) == null && maestros.size() <= CANTIDAD_DE_MAESTROS) {
			maestros.add(docente);
			return true;
		}
		return false;
	}

	public Boolean bajaDocente(Integer dni) {

		if (buscarMaestroPorDNI(dni) != null) {
			maestros.remove(buscarMaestroPorDNI(dni));
			return true;
		}
		return false;
	}

	public Alumno buscarAlumnoDNI(Integer dni) {
		for (Alumno a : alumnos) {
			if (a.getDni().equals(dni)) {
				return a;
			}
		}
		return null;
	}
	public Integer getCantidadAlumno() {
		return CANTIDAD_DE_ALUMNOS;
	}

	@Override
	public String toString() {
		return "Sala " + color + "\n Maestro=" + maestros + "\n Alumnos=" + alumnos;
	}

	public Integer getCANTIDAD_DE_MAESTROS() {
		return CANTIDAD_DE_MAESTROS;
	}

	

}
