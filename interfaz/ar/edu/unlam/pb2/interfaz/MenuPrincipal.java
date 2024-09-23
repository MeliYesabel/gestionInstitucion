package ar.edu.unlam.pb2.interfaz;

public enum MenuPrincipal {
	
	ASISTENCIA("Tomar asistencia"),  EVALUAR("Cargar notas"),
	GESTIONAR("Gestionar niveles"), DOCENTE("Gestion del personal docente"),
	MATERIAS("Materias de secundaria"),
	SALIR("Salir");
	
	private String etiqueta;
	
	MenuPrincipal(String etiqueta){
		this.setEtiqueta(etiqueta);
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
