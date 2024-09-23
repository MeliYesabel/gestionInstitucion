package ar.edu.unlam.pb2.dominio;

public class Evaluacion {
	private Materia materia;
	private Integer nota;
	
	
	public Evaluacion(Integer nota) {
		this.nota = nota;
	}


	public Evaluacion(Materia materia, Integer nota) {
		this.materia = materia;
		this.nota = nota;
	}


	public Materia getMateria() {
		return materia;
	}


	public void setMateria(Materia materia) {
		this.materia = materia;
	}


	public Integer getNota() {
		return nota;
	}


	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	

}
