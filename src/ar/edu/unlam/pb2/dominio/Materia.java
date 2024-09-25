package ar.edu.unlam.pb2.dominio;

public class Materia {
	private String nombre;
	private Docente docente;
	
	public Materia(String nombre){
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Boolean bajaDocente() {
		if (this.docente != null) {
			this.docente = null;
			return true;
		}
		return false;
	}

	public Boolean asignarDocente(Docente docente, Materia materia) {

		if (tieneExperienciaEnMateria(materia)) {
			setDocente(docente);
			return true;
		}
		return false;

	}
	
	public Boolean tieneExperienciaEnMateria(Materia materia) {

		for (int i = 0; i < docente.getExperiencia().getMaterias().length; i++) {
			if (docente.getExperiencia().getMaterias()[i].getNombre().equals(materia.getNombre())) {
				return true;
			}
		}

		return false;

	}

}
