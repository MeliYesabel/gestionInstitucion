package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class Anio {
	
	private Alumno alumnos[];
	private Docente profesor;
	private Materia materias[];
	private Numero anio;

	public Anio(Numero anio) {
		this.alumnos = new Alumno[10];
		this.anio = anio;
		this.materias = new Materia[15];
	}

	public Alumno[] getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}

	public Docente getProfesor() {
		return profesor;
	}

	public void setProfesor(Docente profesor) {
		this.profesor = profesor;
	}

	public Numero getAnio() {
		return anio;
	}

	public void setAnio(Numero grado) {
		this.anio = grado;
	}
	
	@Override
	public String toString() {
		return anio + ", Docente a cargo= " + profesor + "/n[Alumnos=" + Arrays.toString(alumnos) +  "]/n [Materias="
				+ Arrays.toString(materias) + "]";
	}

	public Boolean aproboAnioPrevio(Alumno alumno) {

		if (alumno != null && alumno.getHistorial().isPrimaria()
				&& alumno.getHistorial().getAnio()[this.anio.ordinal() - 1]) {
			return true;
		}
		return false;
	}

	public Boolean tieneExperienciaEnMateria(Materia materia) {

		for (int i = 0; i < profesor.getExperiencia().getMaterias().length; i++) {
			if (profesor.getExperiencia().getMaterias()[i].getNombre().equals(materia.getNombre())) {
				return true;
			}
		}

		return false;

	}

	public Boolean agregarAlumno(Alumno alumno) {

		if (aproboAnioPrevio(alumno)) {
			for (int i = 0; i < alumnos.length; i++) {
				if (alumnos[i] == null) {
					alumnos[i] = alumno;
				}
			}
		}
		return false;
	}

	public Alumno buscarAlumnoDNI(int dni) {
		for (int i = 0; i < alumnos.length; i++) {
			if (alumnos[i] != null && alumnos[i].getDni() == dni) {
				return alumnos[i];
			}
		}
		return null;
	}

	public Boolean bajaAlumno(int dni) {
		if (buscarAlumnoDNI(dni)!= null) {
			for (int i = 0; i < alumnos.length; i++) {
				if (alumnos[i].getDni() == dni) {
					alumnos[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	public Boolean bajaDocente() {
		if (this.profesor != null) {
			this.profesor = null;
			return true;
		}
		return false;
	}

	public Boolean asignarDocente(Docente profesor, Materia materia) {

		if (tieneExperienciaEnMateria(materia)) {
			setProfesor(profesor);
			return true;
		}
		return false;

	}

	public Boolean tomarAsistencia(Fecha fecha, Alumno alumno, boolean asistencia) {
		Asistencia nueva = new Asistencia(fecha, asistencia);
		if (!existeFecha(fecha)) {
			for (int i = 0; i < alumno.getAsistencias().length; i++) {
				if (alumno.getAsistencias()[i] != null) {
					alumno.getAsistencias()[i] = nueva;
					return true;
				}
			}
		}
		return false;
	}

	private Boolean existeFecha(Fecha fecha) {

		if (alumnos[0] != null && alumnos[0].getAsistencias()[0] != null
				&& alumnos[0].getAsistencias()[0].getFecha().getMes() == fecha.getMes()
				&& alumnos[0].getAsistencias()[0].getFecha().getDia() == fecha.getDia()) {
			return true;

		}
		return false;
	}

	public void cargarNota(Integer nota, Alumno alumno, Materia materia) {
		Evaluacion evaluacion = new Evaluacion(materia, nota);
		for (int i = 0; i < alumno.getEvaluaciones().length; i++) {
			if (alumno.getEvaluaciones()[i] != null) {
				alumno.getEvaluaciones()[i] = evaluacion;
				break;
			}
		}
	}

	public Materia[] getMaterias() {
		return materias;
	}

	public void setMaterias(Materia materias[]) {
		this.materias = materias;
	}

	public Materia buscarMateriaPorNombre(String nombre) {
		for (int i = 0; i < materias.length; i++) {
			if (materias[i] != null && materias[i].getNombre().equals(nombre)) {
				return materias[i];
				
			}
		}

		return null;
	}

	public Boolean agregarMaterias(Materia materia) {
		if (buscarMateriaPorNombre(materia.getNombre()) == null) {
			for (int i = 0; i < materias.length; i++) {
				if (materias[i] == null) {
					materias[i] = materia;
					return true;
				}
			}
		}
		return false;
	}
	
	public Boolean eliminarMateria(Materia materia) {
		if (buscarMateriaPorNombre(materia.getNombre()) != null) {
			for (int i = 0; i < materias.length; i++) {
				if (materias[i] != null && materias[i].getNombre().equals(materia.getNombre())) {
					materias[i] = null;
					return true;
				}
			}
		}
		return false;
	}
}
