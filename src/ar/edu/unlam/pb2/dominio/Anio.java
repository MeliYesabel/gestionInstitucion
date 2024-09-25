package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class Anio {
	
	private ArrayList<Alumno> alumnos;
	private Docente profesor;
	private ArrayList<Materia> materias;
	private Numero anio;

	public Anio(Numero anio) {
		this.alumnos = new ArrayList<>();
		this.anio = anio;
		this.materias = new ArrayList<>();
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
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
		return anio + ", Docente a cargo= " + profesor + "/n[Alumnos=" + (alumnos) +  "]/n [Materias="
				+ (materias) + "]";
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
			alumnos.add(alumno);
			return true;
		}
		return false;
	}

	public Alumno buscarAlumnoDNI(Integer dni) {
		Alumno alumnoBuscado=null;
		for(Alumno alumno : alumnos) {
			if(alumno.getDni().equals(dni)) {
				alumnoBuscado=alumno;
			}
		}
		return alumnoBuscado;
	}

	public Boolean bajaAlumno(Integer dni) {
		Alumno alumnoABajar=buscarAlumnoDNI(dni);
		Boolean bajado=false;
		if (buscarAlumnoDNI(dni)!= null) {
			alumnos.remove(alumnoABajar);
			bajado=true;
			}
		return bajado;
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

	public void cargarNota(Integer nota, Alumno alumno, Materia materia) {
		Evaluacion evaluacion = new Evaluacion(materia, nota);
		for (int i = 0; i < alumno.getEvaluaciones().size(); i++) {
			if (alumno.getEvaluaciones().get(i) != null) {
				alumno.getEvaluaciones().set(i, evaluacion);
				break;
			}
		}
	}

	public ArrayList<Materia> getMaterias() {
		return materias;
	}

	
	public Materia buscarMateriaPorNombre(String nombre) {
		Materia materiaBuscada=null;
		for(Materia materia : materias) {
			if(materia.getNombre().equals(nombre)) {
				materiaBuscada=materia;
			}
		}
		return materiaBuscada;
	}

	public Boolean agregarMaterias(Materia materia) {
		return materias.add(materia);
	}
	
	public Boolean eliminarMateria(Materia materia) {
		Materia materiaABajar=buscarMateriaPorNombre(materia.getNombre());
		Boolean bajado=false;
		if (materiaABajar != null) {
			materias.remove(materiaABajar);
			bajado=true;
		}
		return bajado;
	}
}
