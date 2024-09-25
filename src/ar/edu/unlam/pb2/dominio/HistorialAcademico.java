package ar.edu.unlam.pb2.dominio;

public class HistorialAcademico {
	private Boolean jardinero;
	private Boolean primaria;
	private Boolean grado[];
	private Boolean anio[];
	private Materia materias[];

	public HistorialAcademico(Boolean jardinero) {
		this.jardinero = jardinero;
		this.anio = new Boolean[6];
		this.grado = new Boolean[6];
		this.materias = new Materia[12];
	}

	public Boolean[] getAnio() {
		return anio;
	}

	public void setAnio(Boolean[] anio){
		this.anio = anio;
	}

	public HistorialAcademico() {
		this.jardinero = false;
		this.grado = new Boolean[6];
		this.anio = new Boolean[6];
		this.materias = new Materia[12];

	}

	public Boolean[] getGrado() {
		return grado;
	}

	public void setGrado(Boolean[] grado) {
		this.grado = grado;
	}

	public Materia[] getMaterias() {
		return materias;
	}

	public void setMaterias(Materia[] materias) {
		this.materias = materias;
	}

	public Boolean isJardinero() {
		return jardinero;
	}

	public void setJardinero(Boolean jardinero) {
		this.jardinero = jardinero;
	}

	public Boolean isPrimaria() {
		return primaria;
	}

	public void setPrimaria(Boolean primaria) {
		this.primaria = primaria;
	}
	
	public Boolean agregarMateria(Materia materia) {
		if (buscarMateriaPorNombre(materia.getNombre()) == null) {
			for (Integer i = 0; i < materias.length; i++) {
				if (materias[i] == null) {
					materias[i] = materia;
					return true;
				}
			}
		}
		
		return false;
	}

	public Materia buscarMateriaPorNombre(String nombre) {
		for (Integer i = 0; i < materias.length; i++) {
			if (materias[i] != null && materias[i].getNombre().equals(nombre)) {
				return materias[i];
				
			}
		}

		return null;
	}
	
	public void actualizacionPrimaria() {
		Integer total = 0;
		for (Integer i = 0; i < grado.length; i++) {
			if (grado[i]) {
				total++;
			}
		}
		if (total == 6) {
			setPrimaria(true);

		}
	}
}
