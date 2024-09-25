package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class Anio {
	
	private ArrayList<Alumno> alumnos;
	private ArrayList<Materia> materias;
	private Numero anio;

	public Anio(Numero anio) {
		this.alumnos = new ArrayList<>();		
		this.materias = new ArrayList<>();
		this.anio = anio;
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

	public Numero getAnio() {
		return anio;
	}

	public void setAnio(Numero grado) {
		this.anio = grado;
	}
	
	@Override
	public String toString() {
		return anio + "/n[Alumnos=" + (alumnos) +  "]/n [Materias="+ (materias) + "]";
	}

	public Boolean aproboJardin(Alumno alumno) {
		if (alumno != null && alumno.getHistorial().isPrimaria()) {
			return true;
		}
		return false;
	}
	
	
	
	public Boolean aproboAnioPrevio(Alumno alumno) {
		
		if (alumno != null && aproboJardin(alumno)
				&& alumno.getHistorial().getAnio()[this.anio.ordinal()-1]){
			return true;
		}
		return false;
	}

	public Boolean tieneExperienciaEnMateria(Docente profesor, Materia materia) {
		for (int i = 0; i < profesor.getExperiencia().getMaterias().length; i++) {
			if (profesor.getExperiencia().getMaterias()[i]!=null) {
				if(profesor.getExperiencia().getMaterias()[i].getNombre().equals(materia.getNombre())){
					return true;
					}
			}
		}
		return false;
	}

	public Boolean agregarAlumno(Alumno alumno) {
		Boolean alumnoAgregado=false;
		
		if(getAnio().equals(Numero.PRIMERO)) {
			if(aproboJardin(alumno)) {	
				alumnos.add(alumno);
				alumnoAgregado=true;
			}
		}else{
			if(aproboAnioPrevio(alumno)) {
				alumnos.add(alumno);
				alumnoAgregado=true;	
			}
			
		}
		return alumnoAgregado;
			
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

	public Boolean bajaDocente(Materia materia) {
		if (materia.getDocente() != null) {
			materia.setDocente(null);
			return true;
		}
		return false;
	}

	public Boolean asignarDocente(Docente profesor, Materia materia) {
		if (tieneExperienciaEnMateria(profesor, materia)) {
			materia.setDocente(profesor);
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

		for(Materia materia : materias) {
			if(materia.getNombre().equals(nombre)) {
				return materia;
			}
		}
		return null;
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
